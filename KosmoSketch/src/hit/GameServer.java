package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class GameServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread timerThread = null;
	public GameServer() throws IOException {
		super(Port._TIMER);
		timerThread = new Thread(this);
		timerThread.start();
	}
	@Override
	public void run() {
		boolean isStop = false;
		try {
			while(!isStop) {
				client = this.accept();				
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}