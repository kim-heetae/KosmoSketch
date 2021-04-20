package yj;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinView extends JPanel {

	Font		font_label			= new Font("맑은 고딕", Font.BOLD, 20);
	Font		font_labels			= new Font("맑은 고딕", Font.BOLD, 12);
	JPanel		jp_label			= null;								// 북쪽에 배치."회원가입" 글자 라벨을 담는 패널 선언.
	JPanel		jp_button			= null;								// 남쪽에 배치. [회원가입] 버튼과 [뒤로가기] 버튼을 담는 패널
																		// 선언.
	JPanel		jp_main				= null;								// 중앙에 배치. 매인 패널 선언.
	JLabel		jlb_join			= null;								// "회원가입" 라벨 선언.
	JButton		jbtn_join			= null;								// 회원가입 버튼 선언.
	JButton		jbtn_back			= null;								// 뒤로가기 버튼 선언.
	// 메인 패널에 들어갈 패널 선언
	JPanel		jp_label_left		= null;
	JPanel		jp_textfield_center	= null;
	JPanel		jp_button_right		= null;
	// 메인 패널에 들어갈 라벨 선언
	JLabel		jlb_id				= null;
	JLabel		jlb_pw				= null;
	JLabel		jlb_pw_confirm		= null;
	JLabel		jlb_nickname		= null;
	JLabel		jlb_email			= null;
	JLabel		jlb_code			= null;

	// 메인 패널-중앙 패널에 들어갈 텍스트 필드 선언
	JTextField	jtf_id				= null;
	JTextField	jtf_pw				= null;
	JTextField	jtf_pw_confirm		= null;
	JTextField	jtf_nickname		= null;
	JTextField	jtf_email			= null;
	JTextField	jtf_code			= null;

	// 메인 패널-우측 패널에 들어갈 버튼 선언
	JButton		jbtn_id				= null;
	JButton		transparent1		= null;
	JButton		transparent2		= null;
	JButton		jbtn_nickname		= null;
	JButton		jbtn_email			= null;
	JButton		jbtn_code			= null;
	
	ClientView clientView = null;
	
	int width = 500;
	int height = 400;
	
	public JoinView(ClientView clientView) {
		this.clientView = clientView;
		initDisplay();
	}

	public void initDisplay() {
		BorderLayout bl = new BorderLayout(0, 30);
		this.setLayout(bl);
		// 생성부
		jp_label 							= new JPanel(); 		// "회원가입" 라벨이 들어갈 패널 생성.
		jp_button 							= new JPanel(); 		// [회원가입] 버튼과 [뒤로가기] 버튼이 들어갈 패널 생성.
		jp_main 							= new JPanel(); 		// 메인 패널 생성.
		jp_label_left 						= new JPanel();
		jp_textfield_center 				= new JPanel();
		jp_button_right						= new JPanel();
		jlb_join 							= new JLabel("회원가입"); // "회원가입" 라벨 생성.
		jlb_join.setFont(font_label); // 라벨의 폰트 설정.
		jbtn_join 							= new JButton("회원가입");
		jbtn_back 							= new JButton("뒤로가기");
		jbtn_join.addActionListener(clientView);
		jbtn_back.addActionListener(clientView);
		//메인패널에 붙을 라벨 생성.
		jlb_id 								= new JLabel("ID");
		jlb_pw 								= new JLabel("PW");
		jlb_pw_confirm 						= new JLabel("PW_CONFIRM");
		jlb_nickname					 	= new JLabel("닉네임");
		jlb_email 							= new JLabel("이메일");
		jlb_code 							= new JLabel("인증코드");
		//배열에 담기
		JLabel[] jlb_arr = {jlb_id, jlb_pw, jlb_pw_confirm, jlb_nickname, jlb_email, jlb_code};

		//메인패널-중앙패널에 붙을 텍스트필트 생성.
		jtf_id 								= new JTextField(15);
		jtf_pw 								= new JTextField(15);
		jtf_pw_confirm 						= new JTextField(15);
		jtf_nickname 						= new JTextField(15);
		jtf_email 							= new JTextField(15);
		jtf_code 							= new JTextField(15);
		JTextField[] jtf_arr = {jtf_id, jtf_pw, jtf_pw_confirm, jtf_nickname, jtf_email, jtf_code};
		
		jbtn_id 							= new JButton("중복확인");
		transparent1						= new JButton();
		transparent2						= new JButton();
		jbtn_nickname 						= new JButton("중복확인");
		jbtn_email 							= new JButton("인증번호 전송");
		jbtn_code 							= new JButton("인증번호 확인");
		transparent1.setVisible(false);
		transparent2.setVisible(false);
		JButton[] jbtn_arr = {jbtn_id, transparent1, transparent2, jbtn_nickname, jbtn_email, jbtn_code};
		
		//"회원가입" 라벨 붙이기
		jp_label.add(jlb_join);
		
		//메인 패널 구성 시작
		jp_main.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		//메인 패널-좌측 패널 구성 시작
		jp_label_left.setLayout(new GridLayout(6, 1, 10, 21));
		for(JLabel jlb : jlb_arr) {
			jlb.setFont(font_labels);
		}
		for(JLabel jlb : jlb_arr) {
			jp_label_left.add(jlb);
		}
		//메인 패널-중앙 패널 구성 시작
		jp_textfield_center.setLayout(new GridLayout(6, 1, 10, 17));
		int index = 0;
		for(JTextField jtf : jtf_arr) {
			jp_textfield_center.add(jtf, index++);
		}
		
		//메인 패널-우측 패널 구성 시작
		jp_button_right.setLayout(new GridLayout(6, 1, 10, 10));
		index = 0;
		for(JButton jbtn : jbtn_arr) {
			jp_button_right.add(jbtn, index++);
		}
		
		//메인 패널에 좌측 패널, 중앙 패널, 우측 패널 붙이기
		jp_main.add(jp_label_left);
		jp_main.add(jp_textfield_center);
		jp_main.add(jp_button_right);
		
		//남쪽 패널에 [회원가입] 버튼과 [돌아가기] 버튼 붙이기
		jp_button.add(jbtn_join);
		jp_button.add(jbtn_back);

		//북쪽 패널, 메인 패널, 남쪽 패널 붙이기
		this.add("North", jp_label);
		this.add("South", jp_button);
		this.add("Center", jp_main);
		
	}

}
