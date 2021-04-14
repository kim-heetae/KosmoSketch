package test.project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.*;

//클라이언트쪽. 채팅을 듣고 말하기 구현.
public class ListenerLogic extends Thread implements ActionListener {
	Socket				socket		= null;
	ObjectInputStream	ois			= null;
	ObjectOutputStream	oos			= null;
	JTextArea			jta_client	= new JTextArea();
	JTextArea			jta_display	= new JTextArea();
	JScrollPane			jsp_display	= new JScrollPane(jta_display);
	JPanel				jp_south	= new JPanel();					// 전송버튼과 텍스트필드를 담는 패널
	JTextField			jtf_msg		= new JTextField(20);
	JButton				jbtn_send	= new JButton("전송");

	public ListenerLogic() {
		
		initDisplay();
	}

	public void initDisplay() {
		JFrame jf = new JFrame();

		jp_south.add(jtf_msg);
		jp_south.add(jbtn_send);

		jtf_msg.addActionListener(this);
		jbtn_send.addActionListener(this);

		jf.add("South", jp_south);
		jf.setSize(500, 300);
		jf.setVisible(true);

	}

	public void chat_send() {
		String data = jtf_msg.getText();
		if (data == null || data.length() == 0) {
			return;
		} else {
			oos.writeObject(Protocol._CHAT + "#" + nickname + "#" + data);
		}
	}

	@Override
	public void run() {
		boolean isFlag = false;
		while(!isFlag) {
			String msg = (String)ois.readObject();
		}
	}
	
//	public void init() {
//		try {
//			oos = new ObjectOutputStream(socket.getOutputStream());
//			ois = new ObjectInputStream(socket.getInputStream());
//			oos.writeObject(protocol+"#"+nickName);
//		} catch (Exception e) {
//		}

//				public void send(String msg) {
//				SpeakerLogic soket = new SpekaerLogic();
//				soket.initDisplay();
//				soket.init();
//				
//				@Override
//				public void actionPerformed(ActionEvent ae) {
//					Object obj = ae.getSource();
//					String msg = jtf_msg.getText();
//					if(jbtn_one == obj) {
//						
//					}
//					else if(jtf_msg==obj) {
//						try {
//							oos.writeObject(protocol
//									   +"#"+nickName
//									   +"#"+msg);
//							jtf_msg.setText("");
//						} catch (Exception e) {
//				
//						}
//					}
//					else if(jbtn_exit==obj) {
//						try {
//							oos.writeObject(protocol+"#"+this.nickName);
//				
//							System.exit(0);
//						} catch (Exception e) {
//							
//						}
//
//					}
//					else if(jbtn_change == obj) {
	public static void main(String[] args) {
		new ListenerLogic();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_send || obj == jtf_msg) {
			chat_send();
		}
	}
}