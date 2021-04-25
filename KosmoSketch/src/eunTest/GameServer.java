package eunTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class GameServer extends ServerSocket implements Runnable {

	// 서버가 되기 위해 필요한 선언
	Socket					client					= null;
	ObjectOutputStream		oos						= null;
	ObjectInputStream		ois						= null;
	Room					room					= null;
	GameServerThread		gameServerThread		= null;
	List<GameServerThread>	gameServerThreadList	= null;
	int						portNum					= 0;

	// 게임서버가 관리해야 하는 항목들 선언
	int						readyCount				= 0;		// 게임준비상태인 클라이언트의 수를 셈. 최댓값은 4. 4가 되면 게임이 시작되어야 함.
	boolean					isGamePlay				= false;	// true: 현재 게임 진행중. false: 대기중.
	int						turnCount				= 0;		// 현재 게임의 turn 수를 셈.
	String					questioner				= null;		// 출제자 클라이언트의 닉네임을 담음.

	public GameServer(Room room, int port) throws IOException {
		super(port);
		this.room = room;
		this.portNum = port;
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
				gameServerThreadList.add(gameServerThread);
				gameServerThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
