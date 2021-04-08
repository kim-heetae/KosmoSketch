package time.test.sync;

public class TimeServerTest extends Thread {

	int remainTime = 10;
	boolean isStop;
	
	@Override
	public void run() {
		while (!isStop) {
//			System.out.println(remainTime);
			remainTime--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
