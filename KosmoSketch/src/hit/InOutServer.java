package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class InOutServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread inoutThread = null;
	public InOutServer() throws IOException {
		super(Port._INOUT);
		inoutThread = new Thread(this);
		inoutThread.start();
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
