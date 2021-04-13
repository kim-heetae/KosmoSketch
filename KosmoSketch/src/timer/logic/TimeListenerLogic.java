package timer.logic;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TimeListenerLogic extends JFrame implements Runnable{
	
	JTextArea jta = new JTextArea();
//	JScrollPane jsp = new JScrollPane();
	JPanel jp = new JPanel();
//	int remainTime = 10;
	
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	Thread timeThread = null;
	Thread timeListenThread = new Thread() {
		@Override
		public void run() {
			boolean isStop = false;
			String msg;
			try {
				while(!isStop) {
					msg = ois.readObject().toString();
					jta.append(msg + "\n");
				}
			}
			catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	public TimeListenerLogic() {
		initDisplay();
		try {
			socket = new Socket("localhost", 7375);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
//		timeThread = new Thread(this);
//		timeThread.start();
		timeListenThread.start();
	}
	
	public void initDisplay() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setLayout(new BorderLayout());
//		jsp.add(jta);
//		jp.add(jta);
		this.add(jta);
		this.setSize(700,800);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TimeListenerLogic();
	}

	@Override
	public void run() {
	}

}
