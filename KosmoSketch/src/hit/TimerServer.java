package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class TimerServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread timerThread = null;
	int portNum = 0;
	public TimerServer(int port) throws IOException {
		super(port);
		portNum = port;
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
