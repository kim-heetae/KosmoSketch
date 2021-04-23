package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatClientThread extends Thread {
	//////////// 통신과 관련된 전역변수////////////////
	ObjectInputStream ois; //듣기
	ObjectOutputStream oos; //말하기
	Socket socket;
	////////////// 채팅 화면관련 전역변수 추가 ////////////
	JScrollPane jsp_display = new JScrollPane();
	JTextField jtf_msg = new JTextField(20);
	List<ChatClientThread> list;

	@Override
	public void run() {

		boolean isStop = false;
		while (!isStop) {

		}
	}

}
