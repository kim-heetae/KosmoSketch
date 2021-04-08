package time.test.sync;

public class ServerTest {
	
	TimeServerTest tst = new TimeServerTest();
	
	public ServerTest() {
		ServerThreadTest sts1 = new ServerThreadTest(this);
		ServerThreadTest sts2 = new ServerThreadTest(this);
		ServerThreadTest sts3 = new ServerThreadTest(this);
		
		sts1.th1.start();
		sts1.th.start();
		try {
			Thread.sleep(0);
		}
		catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sts2.th.start();
		sts3.th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tst.start();
	}
	public static void main(String[] args) {
		ServerTest st = new ServerTest();
	}

}
