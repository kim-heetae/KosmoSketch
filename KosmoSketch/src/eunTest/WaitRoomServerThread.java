package eunTest;

import java.io.IOException;
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

	SoS					sos		= null;
	Room				room	= null;
	ObjectOutputStream	oos		= null;
	ObjectInputStream	ois		= null;
	// Room에 있는 클라이언트의 정보(자료구조)는 Room클래스에서 관리함

	WaitRoomServerThread(SoS sos, ObjectOutputStream oos, ObjectInputStream ois) {
		this.sos	= sos;
		this.oos	= oos;
		this.ois	= ois;
	}

	public void broadCasting(String msg) {
		for (WaitRoomServerThread client : sos.clientList.values()) {
			try {
				client.oos.writeObject(msg);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		boolean	isStop	= false;
		String	oisMsg	= null;
		String	oosMsg	= null;
		while (!isStop) {
			try {
				oisMsg = ois.readObject().toString();
				StringTokenizer	st			= new StringTokenizer(oisMsg, "#");
				int				protocol	= Integer.parseInt(st.nextToken());
				String			nickName	= st.nextToken();
				int				roomNum		= 0;
				String			roomName	= null;
				switch (protocol) {
				case Protocol._MAKEROOM:
					roomName = st.nextToken();
					// Room클래스의 변수 초기화는 getter/setter대신 생성자를 이용하기로 한다
					room = new Room(sos.roomList.size(), nickName, roomName);
					sos.roomList.add(room);
					// 대기실에 들어와 있는 클라이언트들의 서버 스레드에게 생성된 방의 [방번호 & 방제목]를 넘겨준다.
					oosMsg = Protocol._ROOM_INFO + Protocol._CUT + nickName + Protocol._CUT
					// 클라이언트는 받은 방번호를 담아 놓아야 함 - 나중에 방 닫을 떄 방번호로 어느 방이 닫히는지 알려줘야 하므로
							+ (sos.roomList.indexOf(room) + 1) + Protocol._CUT + roomName;
					broadCasting(oosMsg);
					break;
				case Protocol._CLOSEROOM:
					// _CLOSEROOM # 닉네임 # 방번호
					roomNum = Integer.parseInt(st.nextToken());
					oosMsg = Protocol._CLOSEROOM + Protocol._CUT + nickName + Protocol._CUT + roomNum;
					broadCasting(oosMsg);
					sos.roomList.remove(roomNum - 1);
					break;
				case Protocol._ROOMIN:
					// _ROOMIN # 닉네임 # 방번호
					roomNum = Integer.parseInt(st.nextToken());
					room = sos.roomList.get(roomNum-1);
					room.nickNameList.add(nickName);
					// 내 담당 클라이언트의 '방 입장'을 다른 서버 스레드들에게도 알린다: [입장하는 클라이언트의 닉네임]과 [입장하는 방의 방번호]를 알려야 함.
					oosMsg = Protocol._ROOMIN + Protocol._CUT + nickName + Protocol._CUT + roomNum;
					broadCasting(oosMsg);
					// inout - chat - timer - paint - game
					oos.writeObject(Protocol._PORT_NUM + Protocol._CUT + room.inoutServer.portNum
													   + Protocol._CUT + room.chatServer.portNum
													   + Protocol._CUT + room.timerServer.portNum
													   + Protocol._CUT + room.paintServer.portNum
													   + Protocol._CUT + room.gameServer.portNum);
					break;
				/////////////////// 이건 클라이언트쪽에 적어줘야 하는 내용임!!
				case Protocol._ROOM_INFO: // 변수에 담아야 할까? 바로 (waitRoom dtm)테이블에 add만 하면 될까?
					roomNum = Integer.parseInt(st.nextToken());
					roomName = st.nextToken();
					
					break;
				////////////////////////////////////////////////
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}