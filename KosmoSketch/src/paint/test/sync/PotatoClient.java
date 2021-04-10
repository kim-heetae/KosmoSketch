package paint.test.sync;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PotatoClient extends JFrame implements KeyListener {
	//////////////// 통신과 관련한 전역변수 추가 시작//////////////
	Socket socket = null;
	ObjectOutputStream oos = null;// 말 하고 싶을 때
	ObjectInputStream ois = null;// 듣기 할 때
	JTextArea jta_client = new JTextArea();
	
	public PotatoClient() {
	}

	public void initDisplay() {
		jta_client.addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("CLIENT 클라이언트");
		this.setLayout(new BorderLayout());
		jta_client.setBackground(Color.CYAN);
		this.add(jta_client);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		PotatoClient pc = new PotatoClient();
		pc.initDisplay();
		pc.init();
//		TimerTest tt = new TimerTest();
//		tt.notify();
	}

	// 소켓 관련 초기화
	public void init() {
		try {
			// 서버측의 ip주소 작성하기
			socket = new Socket("localhost", 3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
//			PotatoClientThread pct = new PotatoClientThread(this);
//			pct.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		Object obj = ke.getKeyCode();
//		char obj = ke.getKeyChar();
		try {
			oos.writeObject(obj);
			System.out.println(obj.toString());
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
