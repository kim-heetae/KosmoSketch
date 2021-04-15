package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

// 서버킹임.
// 10329
// 20329
// 10628
// 10408
// 7375

public class SoS extends JFrame implements Runnable {

	// 선언부___________________________________________________________________________________________
	ServerSocket			waitRoomServerSocket	= null;
	Socket					waitRoomClientSocket	= null;
	ObjectOutputStream		oos_server				= null;
	ObjectInputStream		ois_server				= null;
	WaitRoomServerThread	waitRoomServerThread	= null;

	// 생성자___________________________________________________________________________________________
	public SoS() {
		
	}

	// 대기실을 위한 서버소켓을 생성하는 메소드이다.___________________________________________________________________
	public void createServerSocket() {
		try {
			waitRoomServerSocket = new ServerSocket(Port._WAITROOM);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 콜백메소드 run()___________________________________________________________________________________
	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			try {
				waitRoomClientSocket = waitRoomServerSocket.accept();
				oos_server = new ObjectOutputStream(waitRoomClientSocket.getOutputStream());
				ois_server = new ObjectInputStream(waitRoomClientSocket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new SoS();
	}

	
}
