package eunTest;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

// (●'◡'●)
public class Room {
	InOutServer		inoutServer		= null;
	ChatServer		chatServer		= null;
	TimerServer		timerServer		= null;
	PaintServer		paintServer		= null;
	GameServer		gameServer		= null;
	SoS				sos				= null;
	List<String>	nickNameList	= new Vector<>();
	String			roomName		= null;
	int				roomNum			= 0;

	// 게임 진행과 관련된 선언
	boolean			isGamePlay		= false;			// true: 현재 게임 진행중. false: 대기중.
	int				turnCount		= 0;				// 현재 게임의 turn 수를 셈.
	String			questioner		= null;				// 출제자 클라이언트의 닉네임을 담음.

	// 이 방의 4서버들의 포트번호 선언
	int				chatPort		= 0;
	int				timerPort		= 0;
	int				paintPort		= 0;
	int				gamePort		= 0;

	public Room(int roomNum, String nickName, String roomName) {
		this.roomNum = roomNum;
		this.nickNameList.add(nickName);
		this.roomName = roomName;
		this.chatPort = Port.getPort().getPortNum();
		this.timerPort = Port.getPort().getPortNum();
		this.paintPort = Port.getPort().getPortNum();
		this.gamePort = Port.getPort().getPortNum();
		try {
//			inoutServer		= new InOutServer(Port.getPort().getPortNum());
//			chatServer		= new ChatServer(this, chatPort);
//			timerServer		= new TimerServer(this, timerPort);
//			paintServer		= new PaintServer(this, paintPort);
			gameServer = new GameServer(this, gamePort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
