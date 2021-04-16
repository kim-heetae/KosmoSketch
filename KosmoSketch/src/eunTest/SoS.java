package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

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
	ServerSocket			waitRoomServerSocket	= null;
	SoSView					serverView				= null;
	Socket					waitRoomClientSocket	= null;
	ObjectOutputStream		oos_server				= null;
	ObjectInputStream		ois_server				= null;
	WaitRoomServerThread	waitRoomServerThread	= null;
	// 로그인에 성공하여 대기실에 접속한 클라이언트의 [닉네임]과 [waitRoomServerThread 인스턴스 주소-동기화, broadCastind을 위함]를 담을 자료구조 선언
	Map<String, WaitRoomServerThread> clientList	= null;
	// 방에 대한 정보(방번호, ROOM 인스턴스)를 원본으로 관리하기 위함
	Map<Integer, Room> 		roomList 				= null;

	// 생성자___________________________________________________________________________________________
	public SoS() {
		// 서버소켓 생성 - 서버 start
		createServerSocket();
		clientList = null;
		// 클라이언트가 입력한 id와 pw를 읽어들이고, 그것들을 가지고 오라클 DB에 접근해 아이디 존재여부, 패스워드 일치여부를 판단해 오는 메소드 호출(또는 클래스 생성)
		serverView = new SoSView();
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
				// 클라이언트가 로그인 버튼을 누를 때 클라이언트쪽에서 Socket이 생성되고 곧바로 입력한 id와 pw 정보를 stream을 통해 서버로 보낸다.
				// msg: _LOGIN#아이디#패스워드
				String msg = ois_server.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				String id = st.nextToken();
				String pw = st.nextToken();
				// 데이터 연동을 위한 DAO 클래스 인스턴스 생성
				// insert here
				// DAO 클래스를 통해 로그인 성공 여부(id와 pw 존재, 일치 여부)가 파악되었으면, 그 정보를 클라이언트에게도 알려야 한다.
				/*	DAO 완성되면 주석 제거해야 됨~~~~!
				if("로그인 성공".equals(dao.result)) {
					oos_server.writeObject(Protocol._LOGIN_SUCCESS + Protocol._CUT + "로그인 성공");
				} else if("비밀번호를 확인해주세요.".equals(dao.result)) {
					oos_server.writeObject(Protocol._PW_FAILURE + Protocol._CUT + "로그인 성공");
				} else if("존재하지 않는 아이디입니다.".equals(dao.result)) {
					oos_server.writeObject(Protocol._ID_FAILURE + Protocol._CUT + "로그인 성공");
				} else {
					System.out.println("프로시저 수정????!!!!");
				}
				*/
				clientList = new HashMap<>();
				roomList = new Hashtable<>();
				waitRoomServerThread = new WaitRoomServerThread(this);
//				waitRoomServerThread.client = waitRoomClientSocket;
				Thread th = new Thread(waitRoomServerThread);
				th.start();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new SoS();
	}

	
}
