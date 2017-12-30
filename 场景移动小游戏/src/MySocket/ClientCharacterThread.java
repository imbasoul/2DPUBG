package MySocket;

import java.io.IOException;

public class ClientCharacterThread implements Runnable {
	private Client client;
	
	public ClientCharacterThread(Client client){
		this.client = client;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(20);
				client.writeToServer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
