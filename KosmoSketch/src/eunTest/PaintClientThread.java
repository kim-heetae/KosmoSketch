package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class PaintClientThread extends Thread {

	Socket				paintClient	= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;
	ClientView			clientView	= null;

	// 그림 그리기와 관련된 선언
	// 모드(펜/지우개)는 프로토콜로 전송예정.
	String				mode		= null;
	String				color		= null;
	int					thick		= 0;

	// 생성자
	public PaintClientThread(ClientView clientView) {
		this.clientView = clientView;
		try {
			paintClient = new Socket("localhost", Port._PAINT);
			oos = new ObjectOutputStream(paintClient.getOutputStream());
			ois = new ObjectInputStream(paintClient.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean isStop = false;
		String	msg			= null;
		int		protocol	= 0;
		while (!isStop) {
			try {
				msg = ois.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				switch (protocol) {
				case 1:
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
