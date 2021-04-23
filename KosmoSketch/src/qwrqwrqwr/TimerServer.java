package qwrqwrqwr;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TimerServer extends ServerSocket implements Runnable {

	Socket client = null;
	Thread timerThread = null;
	int portNum = 0;

	int remainTime;
	String timer;
	boolean isGaming = false;
	boolean isServerStop = false; //타이머 서버의 존폐결정 //필요없으면 지우기 

	ObjectOutputStream oos = null;

	TimerServerThread tst = null;

	public TimerServer() throws IOException {
		super();
	}

	public TimerServer(int port) throws IOException {
		super(port);
		portNum = port;
		timerThread = new Thread(this);
		timerThread.start();
	}

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

	@Override
	public void run() {//이게 맞나
		while (!isServerStop) {
			try {
				while (!isGaming) {//사람받아서 서버스레드 만들어주기모드
					client = this.accept();
					tst = new TimerServerThread(client, this);
					tst.start();
				}//4명꽉일때 is Gaming 변화
				while (isGaming) {//시간세기모드
					timeCountingThread.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
