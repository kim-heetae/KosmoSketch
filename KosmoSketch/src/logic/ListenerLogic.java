package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.*;

public class ListenerLogic extends Thread {
	Socket socket = null;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	JTextArea jta_client = new JTextArea();
	JTextArea       jta_display = new JTextArea();
	JScrollPane     jsp_display = new JScrollPane(jta_display);
	JPanel 			jp_south 	= new JPanel();
	JTextField 		jtf_msg 	= new JTextField(20);
	
	public class protocol{
		public final int protocol = 500; 

		
		
	public void init() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(protocol+"#"+nickName);
			
			
		} catch (Exception e) {
		
			public void chat() {
				String data = chat.getText();
				if (data == null || data.length() == 0) {
					return;
				}		
			socket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chat();
					System.out.println("채팅 전송~");
				}
				public void send(String msg) {
				SpeakerLogic soket = new SpekaerLogic();
				soket.initDisplay();
				soket.init();
				
				@Override
				public void actionPerformed(ActionEvent ae) {
					Object obj = ae.getSource();
					String msg = jtf_msg.getText();
					if(jbtn_one == obj) {
						
					}
					else if(jtf_msg==obj) {
						try {
							oos.writeObject(protocol
									   +"#"+nickName
									   +"#"+msg);
							jtf_msg.setText("");
						} catch (Exception e) {
				
						}
					}
					else if(jbtn_exit==obj) {
						try {
							oos.writeObject(protocol+"#"+this.nickName);
				
							System.exit(0);
						} catch (Exception e) {
							
						}

					}
					else if(jbtn_change == obj) {

				

}