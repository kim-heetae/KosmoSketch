package eunTest;

//import java.net.Socket;
//
//public class WaitRoomServerThread extends Thread {
//	
//	SoS sos = null;
//	Socket client = null;
//	
//	public WaitRoomServerThread(SoS sos) {
//		this.sos = sos;
//		this.client = sos.waitRoomClientSocket;
//	}
//	
//	@Override
//	public void run() {
//		
//	}
//	
//}
import java.util.StringTokenizer;

import test.project1.Protocol;

public class WaitRoomServerThread implements Runnable {

	SoS sos = null;

	WaitRoomServerThread(SoS sos) {
		this.sos = sos;
	}

	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			String msg = null;
			try {
				msg = sos.ois_server.readObject().toString().trim();
				StringTokenizer	st			= new StringTokenizer(msg, "#");
				int				protocol	= Integer.parseInt(st.nextToken());
				switch (protocol) {
				case Protocol._MAKEROOM:
					new Room();
					break;
				case Protocol._ROOMIN:
					new Room(this);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}