package MySocket;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import com.alibaba.fastjson.JSON;

import yy1020.Bullet;
import yy1020.Circle;
import yy1020.Dsz;
import yy1020.Poi;
import yy1020.UpdateThread;
import yy1020.mainFrame;

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
                	//接收到服务端消息 reply
                    String reply = reader.readLine();
                    
                    //Bullet消息, kind = 2
                    if(reply.substring(30, 31).equals("2")){
//                    	System.out.println("Bullet");
                    	MsgBullet tmp = JSON.parseObject(reply, MsgBullet.class);
                    	// 只接收其它客户端发来的消息
                    	if(tmp.getPort() == UpdateThread.dsz0.getPort()) continue;                    	               
                    	
                    	/**需要修改**/
                    	for(Dsz dsz : mainFrame.dsz1){
                    		if(dsz.getPort() == tmp.getPort()){
                    			dsz.bullet[tmp.getId()] = new Bullet().fromMsg(tmp);
                    			Thread thb = new Thread(dsz.bullet[tmp.getId()]);
                    			thb.start();
                    			break;
                    		}
                    	}
                    	
//                    	System.out.println("Bu " + "【" + tmp.getX() + "," + tmp.getY() + "】");
                    }
                    
                    //Poi消息, kind = 3
                    else if(reply.substring(9, 10).equals("3")){
                    	System.out.println("##开始缩小毒圈！！##");
                    	
//                    	List<Poi> pointList = JSON.parseArray(reply, Poi.class);
//                    	Poi[] pointArray = new Poi[6];
//                    	pointList.toArray(pointArray);
//                    	
//                    	Circle circle = new Circle();
//                    	circle.setPoint(pointArray);
//        				Thread thcir = new Thread(circle);
//        				thcir.start();
                    }
                    
                    //Character消息, kind = 1
                    else{
//                    	System.out.println("Character");
                    	MsgCharacter tmp = JSON.parseObject(reply, MsgCharacter.class);
                    	if(tmp == null) System.out.println("MsgCharacter Fuck");
                    	else{
                    		// 只接收其它客户端发来的消息
                        	if(tmp.getPort() == UpdateThread.dsz0.getPort()) continue;
                        	
                        	
                        	/**需要修改**/
                        	for(Dsz dsz : mainFrame.dsz1){
                        		if(dsz.getPort() == 0){
                        			dsz.setPort(tmp.getPort());
                        			dsz.fromMsg(tmp);
                        			break;
                        		}
                        		if(dsz.getPort() == tmp.getPort()){
                        			dsz.fromMsg(tmp);
                        			break;
                        		}
                        	}	
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
