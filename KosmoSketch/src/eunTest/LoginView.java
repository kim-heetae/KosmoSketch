package eunTest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel {
	JPanel		jp_north, jp_center, jp_south, jp_east, jp_west, jp_cWest, jp_cCenter;

	JLabel		jlb_logo, jlb_id, jlb_pw;

	JTextField	jtf_id;
	
	JPasswordField jpf_pw;

	JButton		jbtn_login, jbtn_join, jbtn_exit;
	
	ClientView clientView = null;
	
	int width = 300;
	int height = 200;
	
	public LoginView(ClientView clientView) {
		
		this.clientView = clientView;
		
//		JFrame jf = new JFrame();
////////////////////////////////////테스트용 프레임///////////////////////////////////
		this.setLayout(new BorderLayout());

		// north
		jp_north	= new JPanel(new FlowLayout(FlowLayout.CENTER));
		jlb_logo	= new JLabel("KosmoCatch");
		jlb_logo.setFont(new Font("Dialog", Font.BOLD, 30));
		jp_north.add(jlb_logo);
		this.add("North", jp_north);

		// center
		jp_center	= new JPanel(new FlowLayout());
		jp_cWest	= new JPanel(new GridLayout(2, 1, 10, 10));
		jp_cCenter	= new JPanel(new GridLayout(2, 1, 10, 10));
		// center-west
		jlb_id		= new JLabel("ID : ");
		jlb_pw		= new JLabel("PW : ");
		jp_cWest.add(jlb_id);
		jp_cWest.add(jlb_pw);
		jp_center.add("West", jp_cWest);
		// center-center
		jtf_id	= new JTextField(12);
		jpf_pw	= new JPasswordField(12);
		jp_cCenter.add(jtf_id);
		jp_cCenter.add(jpf_pw);
		jp_center.add("Center", jp_cCenter);
		this.add("Center", jp_center);

		// south
		jp_south	= new JPanel(new FlowLayout());
		jbtn_join	= new JButton("회원가입");
		jbtn_login	= new JButton("로그인");
		jbtn_exit	= new JButton("종료");
		// 이벤트 관련 add
		jbtn_join.addActionListener(clientView);
		jbtn_login.addActionListener(clientView);
		jbtn_exit.addActionListener(clientView);
		jtf_id.addActionListener(clientView);
		jpf_pw.addActionListener(clientView);
		jtf_id.addKeyListener(clientView);
		jpf_pw.addKeyListener(clientView);
		jtf_id.setFocusTraversalKeysEnabled(false);
		jpf_pw.setFocusTraversalKeysEnabled(false);
		
		jp_south.add(jbtn_join);
		jp_south.add(jbtn_login);
		jp_south.add(jbtn_exit);
		this.add("South", jp_south);
		// testFrame
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.add(this);
//		jf.setSize(300, 200);
//		jf.setVisible(true);
	}

//	public static void main(String[] args) {
//		new LoginPanel();
//	}


}