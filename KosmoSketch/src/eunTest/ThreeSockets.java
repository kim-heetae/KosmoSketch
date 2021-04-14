package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreeSockets {

	Socket client1 = null;
	Socket client2 = null;
	Socket client3 = null;
	ObjectOutputStream oos1 = null;
	ObjectInputStream ois1 = null;
	ObjectOutputStream oos2 = null;
	ObjectInputStream ois2 = null;
	ObjectOutputStream oos3 = null;
	ObjectInputStream ois3 = null;
	
	public ThreeSockets() {
		try {
			// 시점 중요하다. 생성하고 스트림에 먼저 써주어야 함. 아니면 서버쪽 read에서 막혀있는다.
			client1 = new Socket("localhost", 7777);
			oos1 = new ObjectOutputStream(client1.getOutputStream());
			ois1 = new ObjectInputStream(client1.getInputStream());
			oos1.writeObject("첫번째 클라이언트");
			client2 = new Socket("localhost", 7777);
			oos2 = new ObjectOutputStream(client2.getOutputStream());
			ois2 = new ObjectInputStream(client2.getInputStream());
			oos2.writeObject("두번째 클라이언트");
			client3 = new Socket("localhost", 7777);
			oos3 = new ObjectOutputStream(client3.getOutputStream());
			ois3 = new ObjectInputStream(client3.getInputStream());
			oos3.writeObject("세번째 클라이언트");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ThreeSockets();
	}

}
