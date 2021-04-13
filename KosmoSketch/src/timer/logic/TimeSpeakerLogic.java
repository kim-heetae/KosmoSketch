package timer.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TimeSpeakerLogic extends JFrame {
	innerClass inner = null;
	List<innerClass> list = null;
	JTextArea jta = new JTextArea();
	JPanel jp = new JPanel();
	ServerSocket server = null;
	Socket socket = null;
	String timer;
	Thread timeThread = null;
	Thread serverThread = new Thread() {
		@Override
		public void run() {
			System.out.println("serverThread run start");
			boolean isStop = false;
			list = new Vector<>();
			try {
				server = new ServerSocket(7375);
				jta.append("Server Ready.........\n");
				while(!isStop) {
					socket = server.accept();
					inner = new innerClass();
					list.add(inner);
					jta.append("client info:"+socket+"\n");
					timeThread = new Thread(inner);
					if(list.size() == 1) {
						timeThread.start();		
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	Thread timeappend = new Thread() {
		
	};
	
	
	public TimeSpeakerLogic() {
		initDisplay();
		serverThread.start();
	}
	
	public void initDisplay() {
		jp.add(jta);
		this.add(jp);
		this.setSize(700, 800);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TimeSpeakerLogic();
	}
	
	class innerClass implements Runnable{
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		public innerClass() {
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
					ois = new ObjectInputStream(socket.getInputStream());
					System.out.println(list.size());
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		@Override
		public void run() {
			synchronized (this) {
				boolean isStop = false;
				System.out.println("innerClass run start");
				int remainTime = 10;
				if (socket != null) {
					try {
						while (!isStop) {
							timer = String.format("00:00:%02d", remainTime);
							for (innerClass ic : list) {
								ic.oos.writeObject(timer);
							}
//							oos.writeObject(timer);
							remainTime--;
							Thread.sleep(1000);
							if (remainTime < 0) {
								this.wait();
								remainTime = 10;
							}
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
