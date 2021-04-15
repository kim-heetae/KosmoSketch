package eunTest;

import java.io.IOException;
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
	ServerSocket	waitRoomServerSocket	= null;
//	ServerSocket	inoutServerSocket		= null;
//	ServerSocket	chatServerSocket		= null;
//	ServerSocket	timerServerSocket		= null;
//	ServerSocket	paintServerSocket		= null;
	Socket			waitRoomClientSocket	= null;
//	Socket			inoutClientSocket		= null;
//	Socket			chatClientSocket		= null;
//	Socket			timerClientSocket		= null;
//	Socket			paintClientSocket		= null;

	// 생성자___________________________________________________________________________________________
	public SoS() {
		
	}

	// SoS서버소켓을 생성하는
	// 메소드이다.________________________________________________________________________
	public void createServerSocket() {
		try {
			waitRoomServerSocket = new ServerSocket(Port._WAITROOM);
			waitRoomClientSocket = waitRoomServerSocket.accept();
//			inoutServerSocket = new ServerSocket(7375);
//			chatServerSocket = new ServerSocket(7375);
//			timerServerSocket = new ServerSocket(7375);
//			paintServerSocket = new ServerSocket(7375);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

	@Override
	public void run() {
		
	}

}
