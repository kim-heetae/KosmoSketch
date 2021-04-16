package hit;

public class ChatServerThread extends Thread {
	ChatServer chatServer = null;
	public ChatServerThread(ChatServer chatServer) {
		this.chatServer = chatServer;
		this.start();
	}
	@Override
	public void run() {
		
	}
}
