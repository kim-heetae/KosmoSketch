package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextArea;

import eunTest.Port;

public class ChatServer extends ServerSocket implements Runnable {

	Socket					client					= null;
	Thread					chatThread				= null;
	JTextArea				jta_log					= new JTextArea();
	ChatServerThread		chatServerThread		= null;
	List<ChatServerThread>	chatServerThreadList	= null;
	int						portNum					= 0;

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
		// List는 인터페이스, Vector는 List를 구현하는 구현체 클래스임.

	}

	@Override
	public void run() {
		boolean isStop = false;
		chatServerThreadList = new Vector<>();
		try {
			while (!isStop) {
				client = this.accept();
				ChatServerThread chatServerThread = new ChatServerThread(this);
				chatServerThreadList.add(chatServerThread);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
