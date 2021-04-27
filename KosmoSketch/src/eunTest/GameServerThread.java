package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class GameServerThread extends Thread implements Serializable {

	GameServer			gameServer	= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	String				nickName	= null;
	int					totalScore	= 0;
	int					myMapIndex	= 0;

	public GameServerThread(GameServer gameServer, ObjectOutputStream oos, ObjectInputStream ois) {
		this.gameServer = gameServer;
		this.oos = oos;
		this.ois = ois;
//		this.start();
	}

	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		runStart: while (!isStop) {
			try {
				msg = ois.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case Protocol._CLIENT_INFO: // 내 클라이언트가 입장 시 뿌려준 정보임
					this.nickName = st.nextToken();
					for(int i=0; i<gameServer.room.nickNameList.size(); i++) {
						if(gameServer.room.nickNameList.get(i).equals(nickName)) {
							myMapIndex = i;
						}
					}
					System.out.println(nickName + "의 index는? "+myMapIndex);
					////////////////////// 일단은 게임서버스레드가 totalScore를 갖고 있다가 게임을 나갈때 (아래 주석으로 처리한
					////////////////////// 변수를)초기화 시켜주자.
//					gameServer.room.sos.foreServerThread.totalScore = Integer.parseInt(st.nextToken());
					this.totalScore = Integer.parseInt(st.nextToken());
					// gameServer.room.nickNameList.get(nickName): 가져오는 값이 곧 갱신될 라벨의 위치(인덱스)이다.
					// 얘들아 나왔어~~
					String broadMSG = Protocol._NEWBIE_IN + Protocol._CUT
							+ myMapIndex + Protocol._CUT + this.nickName
							+ Protocol._CUT + this.totalScore;
					System.out.println(nickName + "의 인덱스" + myMapIndex);
					gameServer.room.broadCasting(broadMSG, gameServer.gameServerThreadList);
					break;
				case Protocol._USER_INFO: // 이전에 있던 유저인 내 담당 클라이언트의 정보를 broadCasting하자.
					System.out.println("방에 들어와 있던 " + this.nickName + "이 보내주는 인덱스는 "
							+ myMapIndex);
					System.out.println(gameServer.room.nickNameList);
					broadMSG = Protocol._USER_INFO + Protocol._CUT + myMapIndex
							+ Protocol._CUT + this.nickName + Protocol._CUT + this.totalScore;
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println(this.nickName + "의 인덱스===> " + myMapIndex);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					gameServer.room.broadCasting(broadMSG, gameServer.gameServerThreadList);
					break;
				case Protocol._ROOMOUT:
					break runStart;
//				case Protocol._:
//					break;
//				case Protocol._:
//					break;
//				case Protocol._:
//					break;
//				case Protocol._:
//					break;
//				case Protocol._:
//					break;
//				case Protocol._:
//					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
