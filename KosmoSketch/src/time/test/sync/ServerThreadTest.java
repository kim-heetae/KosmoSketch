package time.test.sync;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerThreadTest extends JFrame implements Runnable {

	boolean isStop;
	ServerTest st = null;
	JTextArea jta = new JTextArea();
	Thread th1 = new Thread(this);
	Thread th = new Thread()
//	{
//		@Override
//		public void run() {
//			try {
//				}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			st.tst.remainTime = 1000;
//		}
//	}
	;
	
	public ServerThreadTest(ServerTest st) {
		this.st = st;
	}
	
	@Override
	public void run() {
		this.add("Center", jta);
		this.setSize(500,400);
		this.setVisible(true);
		while (!isStop) {
//			if(st.tst.remainTime < 0) {
//				st.tst.remainTime = 59;
//			}
				try {
					Thread.sleep(3000);
					synchronized (st.tst) {
						st.tst.wait();
						Thread.sleep(5000);
//						System.exit(0);
						st.tst.notify();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
			jta.append(String.valueOf(st.tst.remainTime)+"\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
