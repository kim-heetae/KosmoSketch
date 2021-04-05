package timer.test;

public class TimerTest implements Runnable{

	int timerSet = 3;
	boolean isON = false;
	String currentTime;
	PotatoServer ps = new PotatoServer();
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
				currentTime = String.format("00:00:%02d", timerSet);
				System.out.println(currentTime);
				timerSet--;
				Thread.sleep(1000);
//				ps.jta_server.setText(currentTime);
				if(timerSet < 0) {
//					서버에 알리고
					try {
						while(isON != true) {
							this.wait();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {
		}
		
	}

}
