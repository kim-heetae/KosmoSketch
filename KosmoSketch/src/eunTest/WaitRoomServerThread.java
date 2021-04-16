package eunTest;

//import java.net.Socket;
//
//public class WaitRoomServerThread extends Thread {
//	
//	SoS sos = null;
//	Socket client = null;
//	
//	public WaitRoomServerThread(SoS sos) {
//		this.sos = sos;
//		this.client = sos.waitRoomClientSocket;
//	}
//	
//	@Override
//	public void run() {
//		
//	}
//	
//}
import java.util.StringTokenizer;

import test.project1.Protocol;

public class WaitRoomServerThread implements Runnable {

	SoS sos = null;
	Room room = null;
	// Room에 있는 클라이언트의 정보(자료구조)는  Room클래스에서 관리함

	WaitRoomServerThread(SoS sos) {
		this.sos = sos;
	}

	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			String msg = null;
			try {
				msg = sos.ois_server.readObject().toString();
				StringTokenizer	st			= new StringTokenizer(msg, "#");
				int				protocol	= Integer.parseInt(st.nextToken());
				String			nickName	= st.nextToken();
				switch (protocol) {
				case Protocol._MAKEROOM:
					String roomName = st.nextToken();
					// Room클래스의 변수 초기화는 getter/setter대신 생성자를 이용하기로 한다
					room = new Room(nickName, roomName);
					break;
				case Protocol._ROOMIN:
					new Room(this);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}