package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class GameServerThread extends Thread {

	GameServer			gameServer	= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	String				nickName	= null;
	int					totalScore	= 0;

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
				case Protocol._CLIENT_INFO:	//  내 클라이언트가 입장 시 뿌려준 정보임
					this.nickName = st.nextToken();
					////////////////////// 일단은 게임서버스레드가 totalScore를 갖고 있다가 게임을 나갈때 (아래 주석으로 처리한
					////////////////////// 변수를)초기화 시켜주자.
//					gameServer.room.sos.foreServerThread.totalScore = Integer.parseInt(st.nextToken());
					this.totalScore = Integer.parseInt(st.nextToken());
					// gameServer.room.nickNameList.get(nickName): 가져오는 값이 곧 갱신될 라벨의 위치(인덱스)이다.
					String broadMSG = Protocol._LABEL_UPDATE + Protocol._CUT + gameServer.room.sos.foreServerThread.myMapIndex
									+ Protocol._CUT + this.nickName + Protocol._CUT + this.totalScore;
					gameServer.room.broadCasting(broadMSG, gameServer.gameServerThreadList);
					broadMSG = String.valueOf(Protocol._NEWBIE_IN);
					gameServer.room.broadCasting(broadMSG, gameServer.gameServerThreadList);
					break;
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
