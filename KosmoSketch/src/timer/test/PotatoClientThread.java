package timer.test;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

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
				String msg = pc.ois.readObject().toString();
				StringTokenizer st =new StringTokenizer(msg, "#");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case 600:
					JOptionPane.showMessageDialog(pc, "인원초과로 접속할 수 없습니다.");
					pc.dispose();
					break;
				default:
					break;
				}
//				pc.jta_client.setText(pc.ois.readObject().toString());
			} catch (Exception e) {
			}
		} //////////////////// end of while
	}
}
