package yj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class PaintServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread paintThread = null;
	int portNum = 0;
	public PaintServer(int port) throws IOException {
		super(port);
		portNum = port;
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
