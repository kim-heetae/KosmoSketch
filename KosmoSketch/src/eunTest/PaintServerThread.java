package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class PaintServerThread extends Thread {

	PaintServer			paintServer	= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	public PaintServerThread(PaintServer paintServer, ObjectOutputStream oos, ObjectInputStream ois) {
		this.paintServer = paintServer;
		this.oos = oos;
		this.ois = ois;
		this.start();
	}

	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		while (!isStop) {
			try {
				msg = ois.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case 100:
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
