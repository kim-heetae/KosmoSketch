package qwrqwrqwr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import qwrqwrqwr.Protocol;


public class ServerThreadLogic extends Thread{
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerLogic sl = null;
	
	public ServerThreadLogic(ServerLogic sl) {
		this.sl = sl;
		this.start();
	}
	public void broadCasting(String msg) {
		for (ServerThreadLogic stl : sl.globalList) {
			send(msg);
		}
	}
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(sl.socket.getOutputStream());
			ois = new ObjectInputStream(sl.socket.getInputStream());
//			broadCasting(Protocol._CLIENT_INFO + );
			boolean isStop = false;
			while(!isStop){
				try {
					String msg = ois.readObject().toString().trim();
					StringTokenizer st = new StringTokenizer(msg, "#");
					int protocol = Integer.parseInt(st.nextToken());
					switch (protocol) {
					case Protocol._READY:
						//sl.readycount를 1씩 증가시킨다.
						//globalList의 사이즈가 2 이상이고
						//readycount 가 globalList.size()와 같아질때 게임을 시작
						//Protocol._START를 사용하여 oos로 ClientThread에게 게임이 시작했음을 알려준다.
						//게임을 시작하면 dap을 random으로 정하고 Protocol._DAP을 사용하여 oos로 ClientThread에게 알려준다.
						//타이머를 작동시킨다.
						 
						break;
						
					case Protocol._NOT_READY:	
//						sl.readyCount--;
						break;
					case Protocol._CHAT:
//						String 클라정보 = st.nextToken();
//						String msg = st.nextToken();
//						
//						broadCasting(Protocol_CHAT + "#" + 클라정보 + "#" + msg);
						break;
					
					case Protocol._PAINT:
//						broadCasting(Protocol._PAINT + "#" + 클라정보 + "#" + startX + "#"
//														   + startY+ "#" + endX + "#" + endY);
//						plan1. 그림은 클라에서 ois로 받을때 자신이 questioner라면 Protocol._PAINT로 들어온 msg는 모두 무시한다?
//						plan2. 아니라면 broadCasting메소드 안에 if문추가해서 보낸 사람은 받지못하게 한다? 면은 다른 case들도 바꿔준다?
						break;
					case Protocol._ERASE:
						
						break;
					case Protocol._EXIT:
//						broadCasting(Protocol._EXIT + "#" + 유저정보 + "#" + nickName);
//						sl.globalList.remove(클라정보); - nickname이 아닌 클라정보로
						break;		
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}
