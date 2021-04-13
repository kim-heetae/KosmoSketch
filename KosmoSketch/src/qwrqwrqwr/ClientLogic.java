package qwrqwrqwr;

import java.awt.Container;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class ClientLogic {
	Container cont = null;
	
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	

	
	public void init() {

		try {
			socket = new Socket("localhost", 16332);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	

	public static void main(String[] args) {
		
	}


}
