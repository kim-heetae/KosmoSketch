package test.project1;

import java.io.IOException;
import java.net.ServerSocket;

// 서버킹임.
// 10329
// 20329
// 10628
// 10408
// 7375

public class SoS {

	// 선언부___________________________________________________________________________________________
	ServerSocket	sos_ServerSocket5	= null;
	ServerSocket	sos_ServerSocket1	= null;
	ServerSocket	sos_ServerSocket2	= null;
	ServerSocket	sos_ServerSocket3	= null;

	// 생성자___________________________________________________________________________________________
	public SoS() {
		createServerSocket();
	}

	// SoS서버소켓을 생성하는
	// 메소드이다.________________________________________________________________________
	public void createServerSocket() {
		boolean isStop = false;
		try {
			sos_ServerSocket5 = new ServerSocket(7375);
//			sos_ServerSocket1 = new ServerSocket(7371);
//			sos_ServerSocket2 = new ServerSocket(7372);
//			sos_ServerSocket3 = new ServerSocket(7373);
			while (!isStop) {
				System.out.println("서버 가동 중");
				System.out.println(sos_ServerSocket5.accept());
//				System.out.println(sos_ServerSocket1.accept());
//				System.out.println(sos_ServerSocket2.accept());
//				System.out.println(sos_ServerSocket3.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SoS();
	}

}
