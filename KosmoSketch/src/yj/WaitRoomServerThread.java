package yj;

import java.util.StringTokenizer;

import test.project1.Protocol;

public class WaitRoomServerThread implements Runnable {

	TestServer ts = null;

	WaitRoomServerThread(TestServer ts) {
		this.ts = ts;
	}

	@Override
	public void run() {
		while (true) {
			String msg = null;
			try {
				msg = ts.ois.readObject().toString().trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

		}
	}

}
