package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class ChatServer extends ServerSocket implements Runnable{
	Socket client = null;
	Thread chatThread = null;
	ChatServerThread chatServerThread = null;
	public ChatServer() throws IOException {
		super(Port._CHAT);
		chatThread = new Thread(this);
		chatThread.start();
	}
	@Override
	public void run() {
		boolean isStop = false;
		try {
			while(!isStop) {
				client = this.accept();      //
			}			 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
