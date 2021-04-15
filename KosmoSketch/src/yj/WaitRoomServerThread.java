package yj;

import java.io.IOException;
import java.util.StringTokenizer;

public class WaitRoomServerThread implements Runnable {

	TestServer ts;

	String imsi = null;
	public static final int _MAKEROOM = 1;
	public static final int _ROOMIN = 2;
	
	WaitRoomServerThread(TestServer ts) {
		this.ts = ts;
	}

	@Override
	public void run() {
		while (true) {
			try {
				imsi = ts.ois.readObject().toString().trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
			StringTokenizer st = new StringTokenizer(imsi, "#");
			
			int ptc = Integer.parseInt(st.nextToken());
			
			switch (ptc) {
			case _MAKEROOM:
				new Room();
				break;

			case _ROOMIN:
				new Room(this);
				break;
			}

		}
	}

}
