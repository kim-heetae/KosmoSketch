package paint.test.sync;

import java.awt.BasicStroke;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

public class PotatoClientThread extends Thread{
	
//	PotatoClient pc = null;
	PaintThreadTest ptt = null;
	int startX;
	int startY;
	int endX;
	int endY;
	
//	public PotatoClientThread(PotatoClient pc) {
//		this.pc = pc;
//	}
	public PotatoClientThread(PaintThreadTest ptt) {
		this.ptt = ptt;
	}
	
	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			try {
				String msg = ptt.ois.readObject().toString();
				System.out.println("읽어들이는 중");
				System.out.println(msg);
				StringTokenizer st =new StringTokenizer(msg, "#");
				startX = Integer.parseInt(st.nextToken());
				startY = Integer.parseInt(st.nextToken());
				endX = Integer.parseInt(st.nextToken());
				endY = Integer.parseInt(st.nextToken());
				ptt.g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,0)); //선굵기
                ptt.g.drawLine(startX+10, startY+33, endX+10, endY+33);
//				pc.jta_client.setText(pc.ois.readObject().toString());
			} catch (Exception e) {
			}
		} //////////////////// end of while
	}
}
