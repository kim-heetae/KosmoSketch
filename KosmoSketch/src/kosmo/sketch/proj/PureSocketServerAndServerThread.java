package kosmo.sketch.proj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

class PureServer implements Runnable {

	ServerSocket			server		= null;
	Socket					client		= null;
	PureSocketServerAndServerThread			pl			= null;
	Vector<PureSocketServerAndServerThread>	clientList	= null;

	public PureServer() {
		try {
			server = new ServerSocket(1996);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread th1 = new Thread(this);
		th1.start();
	}

	@Override
	public void run() {
		clientList = new Vector<>();
		boolean isStop = false;
		while (!isStop) {
			try {
				client = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pl = new PureSocketServerAndServerThread(this);
			pl.th.start();
			clientList.add(pl);
		} ///////////// end of while
	}////////////////// end of run

	public static void main(String[] args) {
		new Server();
	}

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////[[클래스 구분선]]////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//한 클라이언트의 패널 위에 발생하는 마우스 이벤트를 읽어들여 처리하고 전송까지 하는 역할을 담당하는 클래스이다.
public class PureSocketServerAndServerThread implements Runnable {

	ObjectOutputStream		oos			= null;
	ObjectInputStream		ois			= null;
	String	client_nickname	= null;
	PureServer	original_server	= null;
	Thread	th	= new Thread() {
		@Override
		public void run() {
			try {
				String			msg			= (String) ois.readObject();
				StringTokenizer	st			= new StringTokenizer(msg, "#");
				int				protocol	= Integer.parseInt(st.nextToken());
				switch (protocol) {
				case Protocol._CLIENT_INFO:
					client_nickname = st.nextToken();
					System.out.println("닉네임: " + client_nickname);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} ///////// end of try-catch
		}
	};
	
	public PureSocketServerAndServerThread(PureServer original_server) {
		this.original_server = original_server;
		try {
			oos = new ObjectOutputStream(original_server.client.getOutputStream());
			ois = new ObjectInputStream(original_server.client.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////////// 필요한 코드 start
	// 닉네임으로 <내가 그리는 중인지> 아니면 <남이 그리는 중인지>를 구분할 수 있다.
	// 내가 그리는 경우:
	// 남이 그리는 경우:
	
	@Override
	public void run() {
		System.out.println("PainterLogic의 run 호출 성공");
		boolean isStop = false;
		while (!isStop) {
			try {
				System.out.println(original_server);
				String			msg	= (String) ois.readObject();
				StringTokenizer	st	= new StringTokenizer(msg, "#");
				oos.writeObject(Protocol._PAINT + "#" + client_nickname);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} ///////////// end of try-catch
		} ///////////////// end of while
	}////////////////////// end of run
		////////////////////////////////////////////////////////////////// 필요한 코드 end

}
