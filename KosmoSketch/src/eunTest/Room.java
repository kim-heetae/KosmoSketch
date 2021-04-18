package eunTest;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import eunTest.SoS;

public class Room {
	InOutServer inoutServer = null;
	ChatServer chatServer  = null;
	TimerServer timerServer = null;
	PaintServer paintServer = null;
	SoS sos = null;
	List<String> nickNameList = new Vector<>();
	String roomName = null;
	int roomNum = 0;
	public Room() {

	}
	public Room(int roomNum, String nickName, String roomName) {
		this.roomNum = roomNum;
		this.nickNameList.add(nickName);
		this.roomName = roomName;
		try {
			inoutServer = new InOutServer();
			chatServer = new ChatServer();
			timerServer = new TimerServer();
			paintServer = new PaintServer();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
