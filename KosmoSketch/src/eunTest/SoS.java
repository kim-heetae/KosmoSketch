package eunTest;

import java.io.IOException;
import java.net.ServerSocket;

// 서버킹임.
// 10329
// 20329
// 10628
// 10408
// 7375

public class SoS {

	//선언부___________________________________________________________________________________________
	ServerSocket sos_ServerSocket = null;
	
	//생성자___________________________________________________________________________________________
	public SoS() {
		createServerSocket();
	}
	
	// SoS서버소켓을 생성하는 메소드이다.________________________________________________________________________
	public void createServerSocket() {
		try {
			sos_ServerSocket = new ServerSocket(7375);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}

}
