package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class GameClientThread extends Thread {
	ClientView			clientView	= null;
	Socket				client		= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	public GameClientThread(Socket client, ClientView clientView) {
		this.client = client;
		this.clientView = clientView;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			// 게임서버스레드에게 나의 닉네임과(DB-서버를 거쳐 받아온)누적점수를 알려준다.
			oos.writeObject(Protocol._CLIENT_INFO + Protocol._CUT + clientView.myNickname + Protocol._CUT
					+ clientView.totalScore);
			// 내가 들어왔다는 사실을 
			oos.writeObject(Protocol._NEWBIE_IN + Protocol._CUT + clientView.myNickname + Protocol._CUT
					+ clientView.totalScore);
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
				case Protocol._LABEL_UPDATE:
					// 몇번째 라벨에 붙일거야?--
					int labelIndex = Integer.parseInt(st.nextToken());
					// 라벨에 붙일 닉네임이 뭐야?--
					String labelNickName = st.nextToken();
					// 라벨에 붙일 누적점수가 뭐야?--
					int labelTotalScore = Integer.parseInt(st.nextToken());
					// 이제 라벨에 붙이자!
					clientView.game.users.get(labelIndex)[0].setText(labelNickName);
					clientView.game.setResizeFont(clientView.game.users.get(labelIndex)[0]);
//					System.out.println(clientView.game.users.get(labelIndex)[0]);
//					System.out.println(clientView.game.users.get(labelIndex));
//					System.out.println("요기"+clientView.game.users.get(labelIndex)[2]);
					clientView.game.users.get(labelIndex)[2].setText(String.valueOf(labelTotalScore));
					break;
				case Protocol._NEWBIE_IN:
					labelIndex = Integer.parseInt(st.nextToken());
					labelNickName = st.nextToken();
					labelTotalScore = Integer.parseInt(st.nextToken());
					clientView.game.users.get(labelIndex)[0].setText(labelNickName);
					clientView.game.setResizeFont(clientView.game.users.get(labelIndex)[0]);
					clientView.game.users.get(labelIndex)[2].setText(String.valueOf(labelTotalScore));
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
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
