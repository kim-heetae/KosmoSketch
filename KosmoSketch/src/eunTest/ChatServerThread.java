package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class ChatServerThread extends Thread {
	
	ChatServer chatServer = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public ChatServerThread(ChatServer chatServer) {
		this.chatServer = chatServer;
		try {
			oos = new ObjectOutputStream(chatServer.client.getOutputStream());
			ois = new ObjectInputStream(chatServer.client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}

	@Override
	public void run() {
		boolean isStop = false;
		String msg = null;
		StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
		try {
			while (!isStop) {
				msg = ois.readObject().toString();
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				// 입장시 "[닉네임]님이 입장하였습니다."를 출력.
				case Protocol._ROOM_WELCOME:
					break;
				// 퇴장시 "[닉네임]님이 퇴장하였습니다."를 출력
				case Protocol._EXIT:
					break;

//					case 
//					Protocol._CLIENT_INFO:
//						break;

				// 채팅을 입력했을떄 |채팅입력을 안했을시 취소 | 채팅을 보냈을때
				case Protocol._CHAT:
					break;
				// 정답인지 일반채팅인지 체크하기
				case Protocol._CORRECT:
					break;
				//게임이 시작 되었을떄 | 답을 맞췄을시 게임 재시작| 타이머가 종료되었을떄 재 시작 
				case
				Protocol._START: 
					break;						
				// 정답을 맞췄을때 "★★[닉네임] 님 정답★★" 출력
				case Protocol._DAP:
					break;

//					//준비
//					case Protocol._READY:
//						break;
//					//준비가 안되었을 떄 
//					case Protocol._NOT_READY:
//						 break; 
//						 //인원이 4명 초과시 새로 접속하려는 사람에게 JOptionPane.ShowMessageDialog 으로  "인원을 초과하였습니다"를 알려주기 
//				    case Protocol._OVER_MEM: 
//						 break; 
					
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
