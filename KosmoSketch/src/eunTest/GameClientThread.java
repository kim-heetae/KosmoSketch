package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class GameClientThread extends Thread implements Serializable {
	ClientView			clientView	= null;
	Socket				client		= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	public GameClientThread(Socket client, ClientView clientView) {
		// 소켓정보 담기
		this.client = client;
		this.clientView = clientView;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			// 게임서버스레드에게 나의 닉네임과(DB-서버를 거쳐 받아온)누적점수를 알려준다.
			oos.writeObject(Protocol._CLIENT_INFO + Protocol._CUT + clientView.myNickname + Protocol._CUT
					+ clientView.totalScore);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		while (!isStop) {
			try {
				msg = ois.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case Protocol._NEWBIE_IN:
					// 변수선언 시작
					int labelIndex = 0;
					String labelNickName = null;
					int labelTotalScore = 0;
					// 변수선언 끝
					// 몇번째 라벨에 붙일거야?--
					labelIndex = Integer.parseInt(st.nextToken());
					// 라벨에 붙일 닉네임이 뭐야?--
					labelNickName = st.nextToken();
					if (labelNickName != clientView.myNickname) {
						oos.writeObject(String.valueOf(Protocol._USER_INFO)); // 나말고 다른 사람이 들어왔으므로 내 정보를 알려주세요~~
						oos.flush();
					} else {
						// 라벨에 붙일 누적점수가 뭐야?--
						labelTotalScore = Integer.parseInt(st.nextToken());
						// 이제 라벨에 붙이자!
						clientView.game.users.get(labelIndex)[0].setText(labelNickName);
						clientView.game.setResizeFont(clientView.game.users.get(labelIndex)[0]);
//					System.out.println(clientView.game.users.get(labelIndex)[0]);
//					System.out.println(clientView.game.users.get(labelIndex));
//					System.out.println("요기"+clientView.game.users.get(labelIndex)[2]);
						clientView.game.users.get(labelIndex)[2].setText(String.valueOf(labelTotalScore));
					}
					break;
//				case Protocol._NEWBIE_IN:
//					labelIndex = Integer.parseInt(st.nextToken());
//					labelNickName = st.nextToken();
//					labelTotalScore = Integer.parseInt(st.nextToken());
//					clientView.game.users.get(labelIndex)[0].setText(labelNickName);
//					clientView.game.setResizeFont(clientView.game.users.get(labelIndex)[0]);
//					clientView.game.users.get(labelIndex)[2].setText(String.valueOf(labelTotalScore));
//					break;
				case Protocol._USER_INFO: // 새로운 클라이언트가 입장 시 내가 받게 되는 그 클라이언트의 정보이다.
					labelIndex = Integer.parseInt(st.nextToken());
					labelNickName = st.nextToken();
					labelTotalScore = Integer.parseInt(st.nextToken());
					System.out.println(labelNickName + "이 보내준 인덱스는" + labelIndex);
					clientView.game.users.get(labelIndex)[0].setText(labelNickName);
					System.out.println(clientView.myNickname + "이 받아서 라벨에 세팅하는 닉네임 주인은: " + labelNickName);
					// 폰트 크기 자동설정
					clientView.game.setResizeFont(clientView.game.users.get(labelIndex)[0]);
					clientView.game.users.get(labelIndex)[2]
							.setText(String.valueOf(labelTotalScore));
					System.out.println(clientView.myNickname + "이 받아서 라벨에 세팅하는 닉네임 주인의 토탈점수는: " + labelTotalScore);
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
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
