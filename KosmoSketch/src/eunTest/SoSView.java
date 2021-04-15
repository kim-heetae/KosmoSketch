package eunTest;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SoSView extends JFrame {

	JTextArea	jta_sos	= null;
	JScrollPane	jsp_sos	= null;

	public SoSView() {
		// 생성
		jta_sos = new JTextArea();
		jsp_sos = new JScrollPane(jta_sos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
										 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 세부 설정
		jta_sos.setEditable(false);
		jta_sos.setBackground(Color.LIGHT_GRAY);
		
		// add 하자
		this.add("Center", jsp_sos);
		
		// JFrame 설정
		this.setTitle("Server is running...");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SoSView();
	}

}
