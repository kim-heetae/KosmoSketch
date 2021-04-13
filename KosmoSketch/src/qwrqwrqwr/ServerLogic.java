package qwrqwrqwr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLogic {
	Server serverr = null;
	public ServerLogic(Server server){
		this.serverr = server;
	}
		public void init() {
		ServerSocket server = null;
		boolean isFlag = false;
		
		try {
			server = new ServerSocket(16332);
			while (!isFlag) {
				Socket client = server.accept();
				serverr.jta.append(client.toString()+"입장");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
