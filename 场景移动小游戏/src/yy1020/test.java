package yy1020;

import MySocket.Client;

import static java.lang.Thread.sleep;

import java.io.IOException;

/**
 * ��ʼ��Ϸ
 * @author yy
 *
 */
public class test {
	static mainFrame mf;
	/*public static void main(String[] args) {
		//���ȴӵ�ͼ�ļ��ж����ͼ����
		ReadMapFile.readfile("map1.map");
		//�ö����ĵ�ͼ���鴴����Ϸ���壬��ʼ��Ϸ
		mf = new mainFrame();
		Circle circle = new Circle();
		circle.randCircle();
//		try {
//			sleep(200000);
//		} catch (Exception e) {
//			//TESTING@@@@@@@@@@@@@@@@@@@@@@@
//		}
		Thread thcir = new Thread(circle);
		thcir.start();
		Background bg = new Background();
		Thread thbg = new  Thread(bg);
		thbg.start();

        try {
            Client client = new Client();
            client.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/
	public static void start() {
		//���ȴӵ�ͼ�ļ��ж����ͼ����
				ReadMapFile.readfile("mymap.map");
				//�ö����ĵ�ͼ���鴴����Ϸ���壬��ʼ��Ϸ
				mf = new mainFrame();
				Circle circle = new Circle();
				circle.randCircle();
//				try {
//					sleep(200000);
//				} catch (Exception e) {
//					//TESTING@@@@@@@@@@@@@@@@@@@@@@@
//				}
				Thread thcir = new Thread(circle);
				thcir.start();
				Background bg = new Background();
				Thread thbg = new  Thread(bg);
				thbg.start();

		        try {
		            Client client = new Client();
		            client.load();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
	}

}
