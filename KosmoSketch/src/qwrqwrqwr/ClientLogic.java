package qwrqwrqwr;

import java.awt.Container;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class ClientLogic implements Runnable{
	Container cont = null;
	
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	
	public ClientLogic() {
		try {
			socket = new Socket("localhost", 7375);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void send() {
	}

	public static void main(String[] args) {
		new ClientLogic();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
