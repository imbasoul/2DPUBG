package MySocket;

import com.alibaba.fastjson.JSON;
import yy1020.Dsz;
import yy1020.mainFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Server extends ServerSocket {
    private static final int Server_Port = 610;

    //定义一个容量为50的线程池
    private static ExecutorService service = Executors.newFixedThreadPool(50);

    private static CopyOnWriteArrayList<String> userList = new CopyOnWriteArrayList<String>();
    private static CopyOnWriteArrayList<Task> threadList =  new CopyOnWriteArrayList<Task>();
    private static BlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(50);

    public Server() throws IOException {
        super(Server_Port);
    }

    /**
     * 启动向客户端发送消息的线程，使用线程处理每个客户端发来的消息
     *
     * @throws Exception
     */
    public void load() throws Exception {
        new Thread(new PushMsgTask()).start(); // 开启向客户端发送消息的线程

        while (true) {
            // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = this.accept();
            /**
             * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
             * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
             * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
             */
            // 每接收到一个Socket就建立一个新的线程来处理它
            service.execute(new Task(socket, socket.getPort()));
            Dsz dsz1 = new Dsz(375,375,1);
            dsz1.setPort(socket.getPort());
            MsgCharacter msgCharacter = dsz1.toMsg();
            msgQueue.put(JSON.toJSONString(msgCharacter));
        }
    }

    /**
     * 从消息队列中取消息，再发送给所有客户端
     */
    class PushMsgTask implements Runnable{
        @Override
        public void run() {
            while(true){
                String msg = null;
                try {
                    msg = msgQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(msg != null){
                    for(Task thread : threadList){
                        thread.sendMsg(msg);
                    }
                }
            }
        }
    }

    /**
     * 处理客户端发来的消息线程类
     */
    class Task implements Runnable{
        private Socket socket;
        private BufferedReader reader;
        private Writer writer;
        private String userName;
        private int port;

        /**
         * 构造函数<br>
         * 处理客户端的消息
         * @throws Exception
         */
        public Task(Socket socket, int port) {
            this.socket = socket;
            this.port = port;
            this.userName = String.valueOf(socket.getPort());
            try {
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                this.writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            userList.add(this.userName);
            threadList.add(this);
//            pushMsg("【" + this.userName + "进入了游戏】");
            System.out.println("Form Client[port:" + socket.getPort() + "] "
                    + this.userName + "进入了游戏");
        }

        @Override
        public void run() {
            try {
                while(true){
                    String msg = reader.readLine();
                    if(msg==null) System.out.println("Server.Task.run.msg Fuck");
                    else {
                    	Date day = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                  
                        System.out.println(dateFormat.format(day) + msg);
                        pushMsg(msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    reader.close();
                    socket.close();
                } catch (Exception e) {

                }
                userList.remove(userName);
                threadList.remove(this);
//                pushMsg("【" + userName + "退出了游戏】");
                System.out.println("Form Client[port:" + socket.getPort() + "] "
                        + userName + "退出了游戏");
            }
        }

        /**
         * 准备发送的消息存入队列
         *
         * @param msg
         */
        private void pushMsg(String msg){
            try {
                msgQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sendMsg(String msg){
            try {
                // 将消息发送给客户端
                writer.write(msg);
                writer.write("\n");
                writer.flush();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
