package eunTest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TimerServer extends ServerSocket implements Runnable {

	Socket					client					= null;
	int						portNum					= 0;
	boolean					isStop					= false;

	int						remainTime;
	String					timer;
	boolean					isGaming				= false;

	ObjectOutputStream		oos						= null;
	TimerServerThread		timerServerThread		= null;
	List<TimerServerThread>	timerServerThreadList	= null;

	Thread timeCountingThread = new Thread(this) {// 시간만 세는 스레드 0초가되면 while문 스탑시키고 59초로 초기화후 사망
		@Override
		public void run() {
			boolean isStop = false;
			try {
				remainTime = 59;
				while (!isStop) {
					timer = String.format("00:00:%02d", remainTime);
					remainTime--;
					Thread.sleep(1000);
					if (remainTime == 0) {
						isStop = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public TimerServer() throws IOException {
		super();
	}
	
	@Override
	public void run() {// 연결해오는 클라받아서 서버스레드 할당해주기
		try {
			while (!isStop) {
				client = this.accept();
				timerServerThread = new TimerServerThread(client, this);
				timerServerThread.start();
				while (isGaming) {
					timeCountingThread.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
