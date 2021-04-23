package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameServerThread extends Thread {
	
	GameServer			gameServer			= null;
	ObjectOutputStream	oos					= null;
	ObjectInputStream	ois					= null;
	
	public GameServerThread(GameServer gameServer, ObjectOutputStream oos, ObjectInputStream ois) {
		this.gameServer = gameServer;
		this.oos = oos;
		this.ois = ois;
		this.start();
	}
	
	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		runStart: while (!isStop) {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
