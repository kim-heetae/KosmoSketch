package hit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreeSocketsServer {

	Socket				client	= null;
	ServerSocket		sv		= null;
	ObjectOutputStream	oos		= null;
	ObjectInputStream	ois		= null;

	public ThreeSocketsServer() {
		try {
			ServerSocket sv = new ServerSocket(7777);
			for (int i = 0; i < 3; i++) {
				int j = 0;
				client = sv.accept();
				System.out.println("클라이언트 정보: " + client);
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				try {
					System.out.println(ois.readObject().toString());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ThreeSocketsServer();
	}

}
