package timer.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PotatoServer extends JFrame implements Runnable{

	PotatoServerThread 			pst 		= null;
	Vector<PotatoServerThread> 	globalList 	= null;
	ServerSocket 				server 		= null;
	Socket 						socket 		= null;
	JTextArea 					jta_server  = new JTextArea(10,30);
	String currentTime = null;
	int timerSet = 60;

//	public void getTimer(PotatoServerThread ppst) {
//		currentTime = String.format("00:00:%02d", timerSet);
//		jta_server.setText(currentTime);
//		for(PotatoServerThread pst : globalList) {
//			try {
//				pst.oos.writeObject(currentTime);
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		if(ppst == globalList.get(0)) {
//			timerSet--;
//		}
//	}
	
	@Override
	public void run() {
		globalList = new Vector<>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3002);
			jta_server.append("Server Ready.........\n");
			while(!isStop) {
				socket = server.accept();
				jta_server.append("client info:"+socket+"\n");
				pst = new PotatoServerThread(this);
				
				pst.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTimer() {
		TimerTest tt = new TimerTest();
		System.out.println(tt.currentTime);
		return tt.currentTime;
	}
	
	public void initDisplay() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("SERVER 서버");
		this.setLayout(new BorderLayout());
		jta_server.setBackground(Color.YELLOW);
		jta_server.setEditable(false);
		this.add(jta_server);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		PotatoServer ps = new PotatoServer();
		ps.initDisplay();
		Thread th = new Thread(ps);
		th.start();
	}
	
}
