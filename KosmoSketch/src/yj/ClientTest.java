package yj;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	Socket	client_paint	= null;
	Socket	client_chat		= null;
	Socket	client_timer	= null;
	Socket	client_inout	= null;

	// 생성자
	public ClientTest() {
		getSockets();
	}

	// 소켓생성 메소드
	public void getSockets() {
		try {
			client_paint = new Socket("localhost", 7375);
			client_chat	 = new Socket("localhost", 7375);
			client_timer = new Socket("localhost", 7375);
			client_inout = new Socket("localhost", 7375);
			
//			client_paint = new Socket("localhost", 7375);
//			client_chat	 = new Socket("localhost", 7371);
//			client_timer = new Socket("localhost", 7372);
//			client_inout = new Socket("localhost", 7373);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientTest();
	}

}
