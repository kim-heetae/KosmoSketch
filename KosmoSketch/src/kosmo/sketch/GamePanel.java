package kosmo.sketch;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.*;
 
public class GamePanel extends JPanel {
	JPanel			jp_center, jp_south, jp_sChat, jp_chatANDlog, jp_sRight, jp_tools, jp_users, jp_user1, jp_user2,
			jp_user3, jp_user4;
	// cWest = center.west
	// jp_user1S = user1의 score패널
	JButton			jbtn_ready, jbtn_exit, jbtn_insert, jbtn_modeP, jbtn_cPick, jbtn_modeE, jbtn_eraseAll, jbtn_thick;

	JLabel			jlb_logo, jlb_timer, jlb_nickName1, jlb_scoreTag1, jlb_cumulTag1, jlb_score1, jlb_cumul1,
			jlb_nickName2, jlb_scoreTag2, jlb_cumulTag2, jlb_score2, jlb_cumul2, jlb_nickName3, jlb_scoreTag3,
			jlb_cumulTag3, jlb_score3, jlb_cumul3, jlb_nickName4, jlb_scoreTag4, jlb_cumulTag4, jlb_score4, jlb_cumul4,
			jlb_isReady1, jlb_isReady2, jlb_isReady3, jlb_isReady4;
	// tag - "현재점수", "누적점수" non-tag - int

	JScrollPane		jsp_chat;

	JTextField		jtf_chat, jtf_thick;

	JTextArea		jta_log;

	Canvas			canvas;

	JTable			jtb_rank;

	MainNorthLeft	mnl;

	public GamePanel() {
		initDisplay();
		bgm();
	}

	public void initDisplay() {

		mnl = new MainNorthLeft();

		jp_center = new JPanel();

		jp_south = new JPanel();
		jp_sChat = new JPanel();
		jp_chatANDlog = new JPanel();
		jp_sRight = new JPanel();
		jp_tools = new JPanel();
		jp_users = new JPanel();
		jp_user1 = new JPanel();
		jp_user2 = new JPanel();
		jp_user3 = new JPanel();
		jp_user4 = new JPanel();

//cW 추가하기

		jbtn_modeP = new JButton("펜모드");
		jbtn_cPick = new JButton("색깔선택");
		jbtn_modeE = new JButton("지우개모드");
		jbtn_eraseAll = new JButton("전체지우기");
		jbtn_thick = new JButton("굵기 ▶▶");
		JButton[] jbtns_tool = { jbtn_modeP, jbtn_cPick, jbtn_modeE, jbtn_eraseAll, jbtn_thick };
		jlb_nickName1 = new JLabel("유저1");
		jlb_nickName2 = new JLabel("유저2");
		jlb_nickName3 = new JLabel("유저3");
		jlb_nickName4 = new JLabel("유저4");
		JLabel[] jlb_nicknames = {jlb_nickName1, jlb_nickName2, jlb_nickName3, jlb_nickName4};
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
		jlb_isReady1 = new JLabel("READY!");
		jlb_isReady2 = new JLabel("READY!");
		jlb_isReady3 = new JLabel("READY!");
		jlb_isReady4 = new JLabel("READY!");
		JLabel[] jlbs_ready = { jlb_isReady1, jlb_isReady2, jlb_isReady3, jlb_isReady4 };

		// 버튼 디자인 설정
		for (JButton jbtn : jbtns_tool) {
			jbtn.setBorder(new BevelBorder(BevelBorder.RAISED));
			jbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			jbtn.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
//			jbtn.setToolTipText("툴팁텍스트");
		}
		jbtn_thick.setEnabled(false);
		jbtn_thick.setBorder(new EmptyBorder(getInsets()));
		
		
		
//		jlb_nickName1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumulTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumulTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumulTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumulTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumul1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumul2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumul3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_cumul4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_isReady1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_isReady2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_isReady3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_isReady4.setBorder(new BevelBorder(BevelBorder.RAISED));

		jp_users.setBorder(new TitledBorder(new LineBorder(Color.red), "In GAME"));
		jp_user1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user4.setBorder(new BevelBorder(BevelBorder.RAISED));

		for (JLabel jlb : jlbs_ready) {
			jlb.setBackground(Color.red);
			jlb.setOpaque(true);
			jlb.setHorizontalAlignment(JLabel.CENTER);
			jlb.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		}

		jtf_chat = new JTextField(63);
		jtf_thick = new JTextField(2);

		jta_log = new JTextArea();
		jsp_chat = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		canvas = new Canvas();
		canvas.setBackground(Color.gray);

		jbtn_insert = new JButton("입력");

		jp_south.setLayout(new GridLayout(1, 2, 5, 0));
		jp_south.setPreferredSize(new Dimension(1600, 300));	////////////////////////////////////////
		jp_chatANDlog.setLayout(new BorderLayout());

//		jp_sChat.setPreferredSize(new Dimension(800, 380));
//		jta_log.setPreferredSize(new Dimension(800, 250));
		jta_log.setEditable(false);
		jta_log.setBackground(Color.white);
		jtf_chat.setPreferredSize(new Dimension(900, 30));
		jp_sChat.add(jtf_chat);
		jbtn_insert.setPreferredSize(new Dimension(80, 30));
		jp_sChat.add(jbtn_insert);
		jp_chatANDlog.add("Center", jsp_chat);
		jp_chatANDlog.add("South", jp_sChat);
		jp_south.add(jp_chatANDlog);

		jtf_thick.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
//		jp_tools.setPreferredSize(new Dimension(800, 40));
		jp_tools.setLayout(new GridLayout(1, 5, 18, 0));
		for (JButton jbtn : jbtns_tool) {
			jbtn.setHorizontalAlignment(JLabel.CENTER);
			jp_tools.add(jbtn);
		}
		jp_tools.add(jtf_thick);
		jp_sRight.add(jp_tools);

		jp_users.setLayout(new GridLayout(2, 2, 5, 5));
		jp_users.setPreferredSize(new Dimension(780, 250));	////////////////////////////

//		jp_user1.setPreferredSize(new Dimension(395, 75));
		jp_user1.setLayout(new GridLayout(2, 3, 10, 2));
		jp_user2.setLayout(new GridLayout(2, 3, 10, 2));
		jp_user3.setLayout(new GridLayout(2, 3, 10, 2));
		jp_user4.setLayout(new GridLayout(2, 3, 10, 2));

//		jlb_nickName1.setPreferredSize(new Dimension(50, 55));
//		jlb_nickName1.setFont(jlb_nickName1.getFont().deriveFont(3));
//		jlb_nickName2.setPreferredSize(new Dimension(50, 55));
//		jlb_nickName2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
//		jlb_nickName2.setHorizontalAlignment(JLabel.CENTER);
//		jlb_nickName3.setPreferredSize(new Dimension(50, 55));
//		jlb_nickName3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
//		jlb_nickName3.setHorizontalAlignment(JLabel.CENTER);
//		jlb_nickName4.setPreferredSize(new Dimension(50, 55));
//		jlb_nickName4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
//		jlb_nickName4.setHorizontalAlignment(JLabel.CENTER);
		for(JLabel jlb : jlb_nicknames) {
			jlb.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
			jlb.setHorizontalAlignment(JLabel.CENTER);
			jlb.setOpaque(true);
			jlb.setBackground(Color.GRAY);
			setResizeFont(jlb);
		}
//		setResizeFont(jlb_nickName1);
//		System.out.println(jlb_nickName1.getFontMetrics(jlb_nickName1.getFont()));	////////////////////////////////////////

		jlb_scoreTag1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));

		jp_user1.add(jlb_nickName1);
		jp_user2.add(jlb_nickName2);
		jp_user3.add(jlb_nickName3);
		jp_user4.add(jlb_nickName4);
		jp_user1.add(jlb_scoreTag1);
		jp_user2.add(jlb_scoreTag2);
		jp_user3.add(jlb_scoreTag3);
		jp_user4.add(jlb_scoreTag4);
		jp_user1.add(jlb_score1);
		jp_user2.add(jlb_score2);
		jp_user3.add(jlb_score3);
		jp_user4.add(jlb_score4);
		jp_user1.add(jlb_isReady1);
		jp_user2.add(jlb_isReady2);
		jp_user3.add(jlb_isReady3);
		jp_user4.add(jlb_isReady4);
		jp_user1.add(jlb_cumulTag1);
		jp_user2.add(jlb_cumulTag2);
		jp_user3.add(jlb_cumulTag3);
		jp_user4.add(jlb_cumulTag4);
		jp_user1.add(jlb_cumul1);
		jp_user2.add(jlb_cumul2);
		jp_user3.add(jlb_cumul3);
		jp_user4.add(jlb_cumul4);

		jp_users.add(jp_user1);
		jp_users.add(jp_user2);
		jp_users.add(jp_user3);
		jp_users.add(jp_user4);
		jp_sRight.add(jp_users);

		jp_south.add(jp_sRight);

		jp_center.setLayout(new BorderLayout());
//		canvas.setPreferredSize(new Dimension(1600, 400));
		jp_center.add("West", mnl);
		jp_center.add("Center", canvas);
		// 여기에 cw
//      this.add(jp_center);

		this.setLayout(new BorderLayout(0, 5));
		this.add("Center", jp_center);
		this.add("South", jp_south);

		/////////////////
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(1600, 1000);
		jf.setLocationRelativeTo(null);

	}
	
	public void setResizeFont(JLabel label) {
		int size = label.getText().length();
		label.setFont(label.getFont().deriveFont((float)120/size));
	}
	
	
	public void bgm() {
		 try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src\\kosmo\\sketch\\kosmo_nore.wav"));
			Clip clip = AudioSystem.getClip();
//			clip.stop();
			clip.open(ais);
			clip.start();
		 } catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GamePanel c = new GamePanel();
	}

}