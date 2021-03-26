package sync.test;

import java.util.StringTokenizer;
import java.util.Vector;

public class PotatoClientThread extends Thread{
	
	PotatoClient pc = null;
	
	public PotatoClientThread(PotatoClient pc) {
		this.pc = pc;
	}
	
	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			try {
				pc.jta_client.append(pc.ois.readObject().toString());
			} catch (Exception e) {
			}
		} //////////////////// end of while
	}
}
