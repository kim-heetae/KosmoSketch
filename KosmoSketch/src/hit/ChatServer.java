package hit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import eunTest.Port;

public class ChatServer extends ServerSocket implements Runnable {

	Socket client = null;
	Thread chatThread = null;
	List<ChatServerThread> chatServerThreadList = null;
	int portNum = 0;
//	public ChatServer() throws IOException {
//		super(Port._CHAT);
//		chatThread = new Thread(this);
//		chatThread.start();
//	}
	public ChatServer(int port) throws IOException {
		super(port);
		portNum = port;
		chatThread = new Thread(this);
		chatThread.start();
	}
	@Override
	public void run() {
		boolean isStop = false;
		chatServerThreadList = new Vector<>();
		try {
			while(!isStop) {
				client = this.accept();
				ChatServerThread chatServerThread = new ChatServerThread(this);
				chatServerThreadList.add(chatServerThread);
			}			 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
