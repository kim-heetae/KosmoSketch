package hit;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import eunTest.SoS;

public class Room {
	InOutServer		inoutServer		= null;
	ChatServer		chatServer		= null;
	TimerServer		timerServer		= null;
	PaintServer		paintServer		= null;
	GameServer		gameServer		= null;
	SoS				sos				= null;
	List<String>	nickNameList	= null;
	String			roomName		= null;
	int				roomNum			= 0;

	public Room() {
		try {
			inoutServer		= new InOutServer(Port.getPort().getPortNum());
			chatServer		= new ChatServer(Port.getPort().getPortNum());
			timerServer		= new TimerServer(Port.getPort().getPortNum());
			paintServer		= new PaintServer(Port.getPort().getPortNum());
			gameServer		= new GameServer(Port.getPort().getPortNum());
			nickNameList	= new Vector<>();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Room(int roomNum, String nickName, String roomName) {
		this.roomNum = roomNum;
		this.nickNameList.add(nickName);
		this.roomName = roomName;
	}

}
