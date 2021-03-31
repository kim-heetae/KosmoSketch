package timer.test;

public class TimerTest implements Runnable{

	int timerSet = 59;
	String currentTime;
//	PotatoServer ps = new PotatoServer();
	public static void main(String[] args) {
		TimerTest ts = new TimerTest();
		Thread th = new Thread(ts);
		th.start();
	}

	@Override
	public void run() {
		
		boolean isStop = false; 
		
		try {
			while(!isStop) {
//				currentTime = String.format("00:00:%02d", timerSet);
//				timerSet--;
//				Thread.sleep(1000);
//				ps.jta_server.setText(currentTime);
//				if(timerSet == 0) {
//					서버에 알리고
//					return;
//				}
			}
		} catch (Exception e) {
		}
		
	}

}
