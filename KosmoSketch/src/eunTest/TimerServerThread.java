package eunTest;

import java.io.ObjectOutputStream;
import java.net.Socket;



public class TimerServerThread extends Thread {//시간받아서 담당클라로 토스
	
	TimerServer ts = null;
	Socket client = null;
	boolean isGameEnd = false;;
	
	ObjectOutputStream oos = null;
	

	
	public TimerServerThread() {
		this.start();
	}
	
	public TimerServerThread(Socket client, TimerServer ts) {
		this.client = client;
		this.ts = ts;
		
	}
	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			
			while(!isGameEnd) {
				oos.writeObject(ts.timer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
