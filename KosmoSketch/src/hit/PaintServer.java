package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class PaintServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread paintThread = null;
	public PaintServer() throws IOException {
		super(Port._PAINT);
		paintThread = new Thread(this);
		paintThread.start();
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
