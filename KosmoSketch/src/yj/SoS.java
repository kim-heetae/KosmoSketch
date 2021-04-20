package yj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;

import test.project1.Protocol;

// 서버킹임.
// 10329
// 20329
// 10628
// 10408
// 7375

public class SoS extends JFrame implements Runnable {

	// 선언부___________________________________________________________________________________________
	ServerSocket						waitRoomServerSocket	= null;
	SoSView								serverView				= null;
	Socket								waitRoomClientSocket	= null;
	ObjectOutputStream					oos_server				= null;
	ObjectInputStream					ois_server				= null;
	ServerLoginThread					serverLoginThread		= null;
	WaitRoomServerThread				waitRoomServerThread	= null;
	LoginDAOImpl						loginDAO				= null;
	// 로그인에 성공하여 대기실에 접속한 클라이언트의 [닉네임]과 [waitRoomServerThread 인스턴스 주소-동기화,
	// broadCasting을 위함]를 담을 자료구조 선언
	Map<String, WaitRoomServerThread>	clientList				= null;
	// 방에 대한 정보(방번호, ROOM 인스턴스)를 원본으로 관리하기 위함
//	Map<Integer, Room> 		roomList 				= null;
	List<Room>							roomList				= null;

	// 생성자
	public SoS() {
		// 서버소켓 생성 - 서버 start
		createServerSocket();
		// 클라이언트가 입력한 id와 pw를 읽어들이고, 그것들을 가지고 오라클 DB에 접근해 아이디 존재 여부, 패스워드 일치여부를 판단해 오는
		// 메소드 호출(또는 클래스 생성)
		serverView = new SoSView();
		serverView.jta_sos.append("Server is now running...");
		clientList = new HashMap<>();
		roomList = new Vector<>();
		loginDAO = new LoginDAOImpl();
		Thread th = new Thread(this);
		th.start();

	}

	// 대기실을 위한 서버소켓을 생성하는
	// 메소드
	public void createServerSocket() {
		try {
			waitRoomServerSocket = new ServerSocket(Port._WAITROOM);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 콜백메소드
	// run()
	@Override
	public void run() {
		boolean	isStop	= false;
		String	msg		= null;
		while (!isStop) {
			try {
				waitRoomClientSocket = waitRoomServerSocket.accept();
				serverView.jta_sos.append(waitRoomClientSocket.toString());
				oos_server = new ObjectOutputStream(waitRoomClientSocket.getOutputStream());
				ois_server = new ObjectInputStream(waitRoomClientSocket.getInputStream());
				serverLoginThread = new ServerLoginThread(this, oos_server, ois_server);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SoS();
	}
}
