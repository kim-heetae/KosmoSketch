package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

// 게임방(GamePanel)에 들어온 클라이언트들의 접속을 받아 각각의 클라이언트들을 담당해줄 ServerThread를 생성해준다.
// 주의; 하나의 클라이언트가 아니라 게임방에 접속하는 모든 클라이언트를 관리한다.
//////////////////////////////나 자신이 서버소켓이다////////////////////////
public class PaintServer extends ServerSocket implements Runnable {

	Socket					client				= null;
	PaintServerThread		paintServerThread	= null;
	// painting과 관련한 broadcasting을 위해서 클라이언트(서버스레드) 목록을 관리할 필요가 있다.
	List<PaintServerThread>	clientList			= new Vector<>();
	ObjectOutputStream		oos					= null;
	ObjectInputStream		ois					= null;
	
	Room					room				= null;

	public PaintServer(Room room) throws IOException {
		// ServerSocket을 상속하였기 때문에 super(int port)(부모생성자호출)를 통해 포트번호를 초기화해주어야 한다.
		// 또한 부모가 먼저 메모리에 올라가기 때문에(check필요★★★) 인스턴스변수(static이 아닌 전역변수)는 파라미터로 보낼 수 없다.
		// 즉, 전역변수에 int portNum = Port._PAINT; 선언 후 super(portNum); 하면 에러남.
		super(Port.getPort().getPortNum());
		this.room = room;
		Thread paintServer = new Thread(this);
		paintServer.start();
	}

	@Override
	public void run() {
		boolean isStop = false;
		try {
			while (!isStop) {
				// 클라이언트가 게임방(GamePanel)에 들어왔을 때 (클라이언트)소켓이 생성됨 ===> accept()가 실행되므로 코드가 진행됨
				client = this.accept();
				// 받아온 클라이언트 소켓의 정보로 oos, ois 생성 ===> 서버(스레드)와 클라이언트 스레드와의 소통 창구 마련
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				paintServerThread = new PaintServerThread(this, oos, ois);
				clientList.add(paintServerThread); // 기억해!! 클라이언트가 GamePanel에서 [나가기] 버튼을 누를 시 리스트에서 빼주어야 함.
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// 방에 있는 클라이언트들에게 말하기 구현
	public void broadCasting(String msg) {
		
	}

}
