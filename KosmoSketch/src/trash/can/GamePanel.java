package trash.can;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel {
   JPanel jp_center, jp_south
       , jp_sChat, jp_sRight, jp_tools, jp_users
       , jp_user1, jp_user2, jp_user3, jp_user4;
      //cWest = center.west
   //jp_user1S = user1의 score패널
   JButton jbtn_ready, jbtn_exit, jbtn_insert;
   
   JLabel jlb_logo, jlb_timer, jlb_modeP, jlb_modeE, jlb_cPick
       , jlb_thick, jlb_eraseAll
       , jlb_nickName1, jlb_scoreTag1, jlb_cumulTag1, jlb_score1, jlb_cumul1
       , jlb_nickName2, jlb_scoreTag2, jlb_cumulTag2, jlb_score2, jlb_cumul2
       , jlb_nickName3, jlb_scoreTag3, jlb_cumulTag3, jlb_score3, jlb_cumul3
       , jlb_nickName4, jlb_scoreTag4, jlb_cumulTag4, jlb_score4, jlb_cumul4
       , jlb_isReady1, jlb_isReady2, jlb_isReady3, jlb_isReady4;
             //tag - "현재점수", "누적점수"              non-tag - int   
   JTextField jtf_chat, jtf_thick;
   
   JTextArea jta_log;
   
   Canvas canvas;
   
   JTable jtb_rank;
   
   RankView rv_panel;
   

	
	
	public GamePanel() {
		initDisplay();
		bgm();
	}

	public void initDisplay() {

		rv_panel = new RankView();

		jp_center = new JPanel();

		jp_south = new JPanel();
		jp_sChat = new JPanel();
		jp_sRight = new JPanel();
		jp_tools = new JPanel();
		jp_users = new JPanel();
		jp_user1 = new JPanel();
		jp_user2 = new JPanel();
		jp_user3 = new JPanel();
		jp_user4 = new JPanel();

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
		jlb_isReady1 = new JLabel("reday!");
		jlb_isReady2 = new JLabel("reday!");
		jlb_isReady3 = new JLabel("reday!");
		jlb_isReady4 = new JLabel("reday!");

		jlb_modeP.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_modeE.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_cPick.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_thick.setBorder(new BevelBorder(BevelBorder.RAISED));
		jlb_eraseAll.setBorder(new BevelBorder(BevelBorder.RAISED));
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
		
		jlb_isReady1.setBackground(Color.red);
		jlb_isReady2.setBackground(Color.red);
		jlb_isReady3.setBackground(Color.blue);
		jlb_isReady4.setBackground(Color.blue);
		jlb_isReady1.setOpaque(true);
		jlb_isReady2.setOpaque(true);
		jlb_isReady3.setOpaque(true);
		jlb_isReady4.setOpaque(true);
		
		jp_users.setBorder(new TitledBorder(new LineBorder(Color.red),"In GAME"));
		jp_user1.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user2.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user3.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_user4.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		jtf_chat = new JTextField(60);
		jtf_thick = new JTextField(2);

		jta_log = new JTextArea();

		canvas = new Canvas();
		canvas.setBackground(Color.yellow);

		jbtn_insert = new JButton("입력");

		jp_south.setLayout(new GridLayout(1, 2, 0, 0));
		jp_south.setPreferredSize(new Dimension(1600, 300));

		jp_sChat.setPreferredSize(new Dimension(800, 380));
		jta_log.setPreferredSize(new Dimension(800, 250));
		jp_sChat.add(jta_log);
		jtf_chat.setPreferredSize(new Dimension(780, 40));
		jp_sChat.add(jtf_chat);
		jbtn_insert.setPreferredSize(new Dimension(80, 40));
		jp_sChat.add(jbtn_insert);
		jp_south.add(jp_sChat);

		jlb_modeP.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jlb_modeE.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jlb_cPick.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jlb_thick.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jtf_thick.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jlb_eraseAll.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
		jp_tools.setPreferredSize(new Dimension(800, 40));
		jp_tools.setLayout(new GridLayout(1,5,20,0));
		jp_tools.add(jlb_modeP);
		jp_tools.add(jlb_modeE);
		jp_tools.add(jlb_cPick);
		jp_tools.add(jlb_thick);
		jp_tools.add(jtf_thick);
		jp_tools.add(jlb_eraseAll);
		jp_sRight.add(jp_tools);

		jp_users.setLayout(new GridLayout(2, 2, 0, 0));
		jp_users.setPreferredSize(new Dimension(800, 250));
		
		jp_user1.setLayout(new GridLayout(2, 3, 0, 0));
		jp_user1.setPreferredSize(new Dimension(395, 75));
		jp_user2.setLayout(new GridLayout(2, 3, 0, 0));
		jp_user3.setLayout(new GridLayout(2, 3, 0, 0));
		jp_user4.setLayout(new GridLayout(2, 3, 0, 0));

		jlb_nickName1.setPreferredSize(new Dimension(50, 55));
		jlb_nickName1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_nickName2.setPreferredSize(new Dimension(50, 55));
		jlb_nickName2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_nickName3.setPreferredSize(new Dimension(50, 55));
		jlb_nickName3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_nickName4.setPreferredSize(new Dimension(50, 55));
		jlb_nickName4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));

		jp_user1.add(jlb_nickName1);
		jlb_scoreTag1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_scoreTag4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_score4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_isReady1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_isReady2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_isReady3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_isReady4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumulTag4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_cumul4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		
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
		canvas.setPreferredSize(new Dimension(1600, 620));
		jp_center.add("West", rv_panel);
		jp_center.add("East", canvas);
		// 여기에 cw
//      this.add(jp_center);

		this.setLayout(new BorderLayout());
		this.add("Center", jp_center);
		this.add("South", jp_south);

		/////////////////
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.setVisible(true);
		jf.setSize(1600, 1000);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);

	}
	
	public void bgm() {
		 try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src\\kosmo\\sketch\\kosmo_bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			System.out.println("노래");
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