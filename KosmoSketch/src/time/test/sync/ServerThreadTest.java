package time.test.sync;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerThreadTest extends JFrame implements Runnable {

	boolean isStop;
	ServerTest st = null;
	JTextArea jta = new JTextArea();
	Thread th = new Thread(this);
	
	public ServerThreadTest(ServerTest st) {
		this.st = st;
	}
	
	@Override
	public void run() {
		this.add("Center", jta);
		this.setSize(500,400);
		this.setVisible(true);
		while (!isStop) {
			jta.append(String.valueOf(st.tst.remainTime)+"\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
