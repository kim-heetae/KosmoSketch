package kosmo.sketch;

public class TimerTest implements Runnable{

	int timerSet = 59;

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
				System.out.println(String.format("00:00:%02d", timerSet));
				timerSet--;
				Thread.sleep(1000);
				if(timerSet == 0) {
					//서버에 알리고
					return;
				}
			}
		} catch (Exception e) {
		}
		
	}

}
