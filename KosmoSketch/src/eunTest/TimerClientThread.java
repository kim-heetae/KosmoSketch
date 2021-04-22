package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TimerClientThread extends Thread {
	ObjectInputStream ois = null;
	Socket socket;
	int port = 0; // 여기서 정하지말고 WaitRoomClientThread에서 시간용 포트로 받는걸루 바꾸기
	String time;
	TimerClientThread(){
		
	}
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", port); 
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
 			e.printStackTrace();
		}
		while(socket != null) {
			try {
				time = ois.readObject().toString();
				//클라뷰(원본으로받기)의 시간jlabel time으로 초기화해주기
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
