package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

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
	SoSView					serverView				= null;
	Socket					waitRoomClientSocket	= null;
	ObjectOutputStream		oos_server				= null;
	ObjectInputStream		ois_server				= null;
	WaitRoomServerThread	waitRoomServerThread	= null;
	// 로그인에 성공하여 대기실에 접속한 클라이언트의 [닉네임]과 [waitRoomServerThread 인스턴스 주소-동기화, broadCastind을 위함]를 담는다.
	Map<String, WaitRoomServerThread> clientList	= null;

	// 생성자___________________________________________________________________________________________
	public SoS() {
		//서버소켓 생성 - 서버 start
		createServerSocket();
		serverView = new SoSView();
		serverView.jta_sos.append("Server is now running...");
		serverView.jta_sos.append("Server is now running...");
		serverView.jta_sos.append("Server is now running...");
		serverView.jta_sos.append("Server is now running...");
		
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
