package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends ServerSocket implements Runnable {

	// 서버가 되기 위해 필요한 선언
	Socket				client				= null;
	ObjectOutputStream	oos					= null;
	ObjectInputStream	ois					= null;
	GameServerThread	gameServerThread	= null;
	int					portNum				= 0;

	// 게임서버가 관리해야 하는 항목들 선언
	int					readyCount			= 0;		// 게임준비상태인 클라이언트의 수를 셈. 최댓값은 4. 4가 되면 게임이 시작되어야 함.


	public GameServer(Room room, int port) throws IOException {
		super(port);
		portNum = port;
		Thread timerThread = new Thread(this);
		timerThread.start();
	}

	@Override
	public void run() {
		boolean isStop = false;
		try {
			while (!isStop) {
				client = this.accept();
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				gameServerThread = new GameServerThread(this, oos, ois);
				gameServerThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
