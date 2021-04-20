package yj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import eunTest.Port;

public class InOutServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread inoutThread = null;
	List<Thread> inoutList = null;
	int portNum = 0;
	
	public InOutServer(int port) throws IOException {
		super(port);
		portNum = port;
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
