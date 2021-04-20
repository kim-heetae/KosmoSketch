package yj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class ChatServerThread extends Thread {
	ChatServer chatServer = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	public ChatServerThread(ChatServer chatServer) {
		this.chatServer = chatServer;
		try {
			oos = new ObjectOutputStream(chatServer.client.getOutputStream());
			ois = new ObjectInputStream(chatServer.client.getInputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}
	@Override
	public void run() {
		boolean isStop = false;
		String msg = null;
		StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
			try {
				while(!isStop) {
					msg = ois.readObject().toString();
					int protocol = Integer.parseInt(st.nextToken());
					switch(protocol) {
					case Protocol._CHAT:
						break;
					case Protocol._CORRECT:
						break;
					case Protocol._DAP:
						break;
					case Protocol._READY:
						break;
					case Protocol._NOT_READY:
						break;
					case Protocol._OVER_MEM:
						break;
					case Protocol._START:
						break;
					case Protocol._EXIT:
						break;
					}
				}
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
	}
}
