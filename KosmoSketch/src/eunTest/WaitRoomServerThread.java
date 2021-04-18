package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	int roomNum = 0;
	String roomName = null;
	// Room에 있는 클라이언트의 정보(자료구조)는  Room클래스에서 관리함

	WaitRoomServerThread(SoS sos, ObjectOutputStream oos, ObjectInputStream ois) {
		this.sos = sos;
		this.oos = oos;
		this.ois = ois;
	}

	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			String msg = null;
			try {
				msg = ois.readObject().toString();
				StringTokenizer	st			= new StringTokenizer(msg, "#");
				int				protocol	= Integer.parseInt(st.nextToken());
				String			nickName	= st.nextToken();
				switch (protocol) {
				case Protocol._MAKEROOM:
					roomName = st.nextToken();
					// Room클래스의 변수 초기화는 getter/setter대신 생성자를 이용하기로 한다
					room = new Room(sos.roomList.size(), nickName, roomName);
					sos.roomList.add(room);
					//대기실에 들어와 있는 클라이언트들의 서버 스레드에게 생성된 방의 [방번호 & 방제목]를 넘겨준다.
					for(WaitRoomServerThread client : sos.clientList.values()) {
						client.oos.writeObject(Protocol._ROOM_INFO
											 + Protocol._CUT
											 + nickName
											 + Protocol._CUT
											 + (sos.roomList.indexOf(room)+1)
											 + Protocol._CUT
											 + roomName);
					}
					break;
				case Protocol._ROOMIN:
					roomNum = Integer.parseInt(st.nextToken());
					
					break;
				/////////////////// 이건 클라이언트쪽에 적어줘야 하는 내용임!!
				case Protocol._ROOM_INFO:	// 변수에 담아야 할까? 바로 (waitRoom dtm)테이블에 add만 하면 될까?
					roomNum = Integer.parseInt(st.nextToken());
					roomName = st.nextToken();
					
					break;
				////////////////////////////////////////////////
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}