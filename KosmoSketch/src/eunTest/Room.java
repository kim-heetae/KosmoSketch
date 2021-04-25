package eunTest;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

// (●'◡'●)
public class Room {
	InOutServer				inoutServer		= null;
	ChatServer				chatServer		= null;
	TimerServer				timerServer		= null;
	PaintServer				paintServer		= null;
	GameServer				gameServer		= null;
	SoS						sos				= null;
	Map<String, Integer>	nickNameList	= new Hashtable<>();
	String					roomName		= null;
	int						roomNum			= 0;

	// 이 방의 4서버들의 포트번호 선언
	int						chatPort		= 0;
	int						timerPort		= 0;
	int						paintPort		= 0;
	int						gamePort		= 0;

	public Room(int roomNum, String nickName, String roomName) {
		this.roomNum = roomNum;
		this.nickNameList.put(nickName, 0);
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

	public void broadCasting(String msg, List list) {
		
		Class<? extends Object> classType = list.get(0).getClass();
		
		// list에 담긴 타입이 ChatServerThread인 경우의 broadCasting 구현
		if(classType == ChatServerThread.class) {
			// 파라미터의 list는 제네릭타입이 없기 때문에 제네릭타입을 가진 리스트에 다시 담아준다.
			List<ChatServerThread> plist = list;
			for(ChatServerThread chatServerThreadList : plist) {
				try {
					chatServerThreadList.oos.writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}//////////////end of try - catch
			}//////////////////end of for
			
		// list에 담긴 타입이 TimerServerThread인 경우의 broadCasting 구현
		} else if(classType == TimerServerThread.class) {
			List<TimerServerThread> plist = list;
			for(TimerServerThread timerServerThreadList : plist) {
				try {
					timerServerThreadList.oos.writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
			}//////////////end of try - catch
		}//////////////////end of for
			
		} else if(classType == PaintServerThread.class) {
			List<PaintServerThread> plist = list;
			for(PaintServerThread paintServerThreadList : plist) {
				try {
					paintServerThreadList.oos.writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}//////////////end of try - catch
			}//////////////////end of for
			
		} else if(classType == GameServerThread.class) {
			List<GameServerThread> plist = list;
			for(GameServerThread gameServerThreadList : plist) {
				try {
					gameServerThreadList.oos.writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}//////////////end of try - catch
			}//////////////////end of for
		}//////////////////////end of if - else if
	}//////////////////////////end of broadCasting

}
