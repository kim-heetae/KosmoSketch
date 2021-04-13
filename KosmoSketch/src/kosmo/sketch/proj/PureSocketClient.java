package kosmo.sketch.proj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

//class ClientLogic의 ois를 통해 읽어들인 좌표 정보를 이용해
//(보낸 닉네임이 나 자신이 아니라면) 패널에 그림을 똑같이 구현해주는 역할을 담당하는 클래스이다.
public class PureSocketClient implements Runnable {

	// [test] start
	Socket				client			= null;
	ObjectOutputStream	oos				= null;
	ObjectInputStream	ois				= null;
	String				client_nickname	= null;
	// [test] end

	public PureSocketClient() {
		client_nickname = JOptionPane.showInputDialog("[Paint 단위 테스트] 닉네임을 입력하세요");
		// [test] start
		try {
			client = new Socket("localhost", 1996);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			oos.writeObject(Protocol._CLIENT_INFO + "#" + client_nickname);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// [test] end

	}

	@Override
	public void run() {
		
	}

	// [test] start
	public static void main(String[] args) {
		new PureSocketClient();
	}
	// [test] end
}
