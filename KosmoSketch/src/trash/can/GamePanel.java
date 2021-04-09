package trash.can;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class GamePanel extends JPanel {
	JPanel jp_center, jp_south, jp_cWest, jp_cwBtns
		 , jp_sChat, jp_sRight, jp_tools, jp_users
		 , jp_user1, jp_user2, jp_user3, jp_user4
		 , jp_user1S, jp_user2S, jp_user3S, jp_user4S;
		//cWest = center.west, cwBtns = center.west.buttons
	//jp_user1S = user1의 score패널
	JButton jbtn_ready, jbtn_exit, jbtn_insert;
	
	JLabel jlb_logo, jlb_timer, jlb_modeP, jlb_modeE, jlb_cPick
		 , jlb_thick, jlb_eraseAll
		 , jlb_nickName1, jlb_scoreTag1, jlb_cumulTag1, jlb_score1, jlb_cumul1
		 , jlb_nickName2, jlb_scoreTag2, jlb_cumulTag2, jlb_score2, jlb_cumul2
		 , jlb_nickName3, jlb_scoreTag3, jlb_cumulTag3, jlb_score3, jlb_cumul3
		 , jlb_nickName4, jlb_scoreTag4, jlb_cumulTag4, jlb_score4, jlb_cumul4;
				 //tag - "현재점수", "누적점수"		        non-tag - int	
	JTextField jtf_chat, jtf_thick;
	
	JTextArea jta_log;
	
	Canvas canvas;
	
	JTable jtb_rank;
	
	public GamePanel() {
		initDisplay();
	}
	public void initDisplay(){
		jp_center = new JPanel();
		jp_cWest  = new JPanel();
		jp_cwBtns = new JPanel();
		jp_south  = new JPanel();
		jp_sChat  = new JPanel();
		jp_sRight = new JPanel();
		jp_tools  = new JPanel();
		jp_users  = new JPanel();
		
//cW 추가하기
		
		jlb_modeP = new JLabel("펜모드");
		jlb_modeE = new JLabel("지우개모드");
		jlb_cPick = new JLabel("색깔선택");
		jlb_thick = new JLabel("굵기");
		jlb_eraseAll = new JLabel("전체지우기");
		jlb_nickName1 = new JLabel("유저1");
		jlb_nickName2 = new JLabel("유저2");
		jlb_nickName3 = new JLabel("유저3");
		jlb_nickName4 = new JLabel("유저4");
		jlb_scoreTag1 = new JLabel("현재점수");
		jlb_scoreTag2 = new JLabel("현재점수");
		jlb_scoreTag3 = new JLabel("현재점수");
		jlb_scoreTag4 = new JLabel("현재점수");
		jlb_cumulTag1 = new JLabel("누적점수");
		jlb_cumulTag2 = new JLabel("누적점수");
		jlb_cumulTag3 = new JLabel("누적점수");
		jlb_cumulTag4 = new JLabel("누적점수");
		jlb_score1 = new JLabel("900");
		jlb_score2 = new JLabel("900");
		jlb_score3 = new JLabel("900");
		jlb_score4 = new JLabel("900");
		jlb_cumul1 = new JLabel("900");
		jlb_cumul2 = new JLabel("900");
		jlb_cumul3 = new JLabel("900");
		jlb_cumul4 = new JLabel("900");
		jlb_modeP.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_modeE.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cPick.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_thick.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_eraseAll.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_nickName1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_nickName2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_nickName3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_nickName4.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_scoreTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_scoreTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_scoreTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_scoreTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumulTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumulTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumulTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumulTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_score1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_score2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_score3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_score4.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumul1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumul2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumul3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cumul4.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		jtf_chat = new JTextField(60);
		jtf_thick = new JTextField(2);
		
		jta_log = new JTextArea();
		
		canvas = new Canvas();
		
		jbtn_insert = new JButton("입력");
		
		
		jp_south.setLayout(new GridLayout(1,2,0,0));
		jp_south.setPreferredSize(new Dimension(1600,380));
		
		jp_sChat.setPreferredSize(new Dimension(800,380));
		jta_log.setPreferredSize(new Dimension(800,300));
		jp_sChat.add(jta_log);
		jtf_chat.setPreferredSize(new Dimension(720,40));
		jp_sChat.add(jtf_chat);
		jbtn_insert.setPreferredSize(new Dimension(80,40));
		jp_sChat.add(jbtn_insert);
		jp_south.add(jp_sChat);
		
		jp_tools.add(jlb_modeP);
		jp_tools.add(jlb_modeE);
		jp_tools.add(jlb_cPick);
		jp_tools.add(jlb_thick);
		jp_tools.add(jtf_thick);
		jp_tools.add(jlb_eraseAll);
		jp_sRight.add(jp_tools);
		
		jp_user1.setLayout(new GridLayout(1,2,0,0));
		jp_user2.setLayout(new GridLayout(1,2,0,0));
		jp_user3.setLayout(new GridLayout(1,2,0,0));
		jp_user4.setLayout(new GridLayout(1,2,0,0));
		
		jp_user1.add(jlb_nickName1);
		jp_user2.add(jlb_nickName2);
		jp_user3.add(jlb_nickName3);
		jp_user4.add(jlb_nickName4);
		jp_users.add(jp_user1);
		jp_users.add(jp_user2);
		jp_users.add(jp_user3);
		jp_users.add(jp_user4);
		
		jp_south.add(jp_sRight);
		
		
		
		jp_center.setLayout(new BorderLayout());
		canvas.setPreferredSize(new Dimension(1600,620));
		jp_center.add(canvas);
		//여기에 cw
		
		
		this.setLayout(new BorderLayout());
		this.add("Center", jp_center);
		this.add("South", jp_south);
		
		
		/////////////////
		JFrame jf = new JFrame();
		jf.add(this);
		jf.setVisible(true);
		jf.setSize(1600,1000);
		jf.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		GamePanel c = new GamePanel();
	}

}
