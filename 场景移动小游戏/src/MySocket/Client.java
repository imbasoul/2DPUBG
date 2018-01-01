package MySocket;

import com.alibaba.fastjson.JSON;

import yy1020.Bullet;
import yy1020.Dsz;
import yy1020.UpdateThread;
import yy1020.mainFrame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;

public class Client extends Socket {
    private static final String Server_IP = "123.206.27.121"; // 服务端IP
    private static final int Server_Port = 610;

    public static Socket client;
    private Writer writer;
    private BufferedReader in;

    //构造函数，与服务器建立连接
    public Client() throws IOException {
        super(Server_IP, Server_Port);
        this.client = this;
        mainFrame.client = this;
        System.out.println("Client[" + client.getLocalPort() + "] 进入");
        UpdateThread.dsz0.setPort(client.getLocalPort());
    }

    /**
     * 启动监听收取消息，循环可以不停的输入消息，将消息发送出去
     * msg为JSON字符串
     * @throws Exception
     */
    public void load() throws IOException, InterruptedException {
        this.writer = new OutputStreamWriter(this.getOutputStream(), "UTF-8");
        new Thread(new ReceiveMsgTask()).start(); //// 启动监听
        
        ClientCharacterThread clientThread = new ClientCharacterThread(this);
        Thread write = new Thread(clientThread);
        write.start();
    }
    
    //发送MsgCharacter给Server
    public void writeToServer() throws IOException{
    	if(UpdateThread.dsz0 == null){
            System.out.println("dsz0 Fuck!!");
            return;
        }
    	if(UpdateThread.dsz0 == null) System.out.println("dsz0 为空 Fuck");
        String msg = JSON.toJSONString(UpdateThread.dsz0.toMsg());
        if(msg == null) System.out.println("msg Fuck!!!");
//        System.out.println("send: " + msg); //
        writer.write(msg);
        writer.write("\n");
        writer.flush();

    }
    
    //发送MsgBullet给Server
    public void writeBulletToServer(MsgBullet msgBullet) throws IOException{
    	String msg = JSON.toJSONString(msgBullet);
    	writer.write(msg);
    	writer.write("\n");
    	writer.flush();
    }
    

    /**
     * 监听服务器发来的消息线程类
     */
    class ReceiveMsgTask implements Runnable{
        private BufferedReader reader;

        @Override
        public void run() {
            try {
                this.reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                while(true){
                    String reply = reader.readLine();
              
//                    System.out.println("reply: " + reply);
                    
//                    System.out.println("@@@kind: " + reply.substring(30, 31) + reply.substring(30, 31).equals("2"));
                    
                    //Bullet消息
                    if(reply.substring(30, 31).equals("2")){
                    	System.out.println("Bullet");
                    	MsgBullet tmp = JSON.parseObject(reply, MsgBullet.class);
                    	// 只接收其它客户端发来的消息
                    	if(tmp.getPort() == UpdateThread.dsz0.getPort()) continue;
                    	mainFrame.dsz1.bullet[tmp.getId()] = new Bullet().fromMsg(tmp);
                    	Thread thb=new Thread(mainFrame.dsz1.bullet[tmp.getId()]);
                    	thb.start();
                    	System.out.println("Bu " + "【" + tmp.getX() + "," + tmp.getY() + "】");
                    }
                    //Character消息
                    else{
//                    	System.out.println("Character");
                    	MsgCharacter tmp = JSON.parseObject(reply, MsgCharacter.class);
                    	if(tmp == null) System.out.println("MsgCharacter Fuck");
                    	else{
                    		// 只接收其它客户端发来的消息
                        	if(tmp.getPort() == UpdateThread.dsz0.getPort()) continue;
                        	mainFrame.dsz1.fromMsg(tmp);
//                            System.out.println( "Ch" + " 【" + mainFrame.dsz1.x + ", " + mainFrame.dsz1.y + "】");
                    	}
                    }
                   
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                    client.close();
                    in.close();
                } catch (Exception e) {

                }
            }
        }
    }
}
