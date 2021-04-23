package eunTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel extends JPanel {
	JPanel			jp_center			= null;
	JPanel			jp_south			= null;
	JPanel			jp_sChat			= null;
	JPanel			jp_chatANDlog		= null;
	JPanel			jp_sRight			= null;
	JPanel			jp_tools			= null;
	JPanel			jp_users			= null;
	JPanel			jp_user1			= null;
	JPanel			jp_user2			= null;
	JPanel			jp_user3			= null;
	JPanel			jp_user4			= null;
	// cWest = center.west
	// jp_user1S = user1의 score패널
	JButton			jbtn_insert			= null;
	JButton			jbtn_modeP			= null;
	JButton			jbtn_cPick			= null;
	JButton			jbtn_modeE			= null;
	JButton			jbtn_eraseAll		= null;
	JButton			jbtn_thick			= null;

	JLabel			jlb_logo			= null;
	JLabel			jlb_timer			= null;
	JLabel			jlb_nickName1		= new JLabel("");
	JLabel			jlb_score1			= new JLabel("0");
	JLabel			jlb_totalscore1		= new JLabel("0");
	JLabel			jlb_isReady1		= new JLabel("READY!");
	JLabel			jlb_nickName2		= new JLabel("");
	JLabel			jlb_score2			= new JLabel("0");
	JLabel			jlb_totalscore2		= new JLabel("0");
	JLabel			jlb_isReady2		= new JLabel("READY!");
	JLabel			jlb_nickName3		= new JLabel("");
	JLabel			jlb_score3			= new JLabel("0");
	JLabel			jlb_totalscore3		= new JLabel("0");
	JLabel			jlb_isReady3		= new JLabel("READY!");
	JLabel			jlb_nickName4		= new JLabel("");
	JLabel			jlb_score4			= new JLabel("0");
	JLabel			jlb_totalscore4		= new JLabel("0");
	JLabel			jlb_isReady4		= new JLabel("READY!");
	JLabel[]		jlb_user1			= { jlb_nickName1, jlb_score1, jlb_totalscore1, jlb_isReady1 };
	JLabel[]		jlb_user2			= { jlb_nickName2, jlb_score2, jlb_totalscore2, jlb_isReady2 };
	JLabel[]		jlb_user3			= { jlb_nickName3, jlb_score3, jlb_totalscore3, jlb_isReady3 };
	JLabel[]		jlb_user4			= { jlb_nickName4, jlb_score4, jlb_totalscore4, jlb_isReady4 };
	// 닉네임 - 현재점수 - 누적점수 - ready 순으로 배열에 담았음
	List<JLabel[]>	users				= new ArrayList<>();
	JLabel			jlb_scoreTag1		= null;
	JLabel			jlb_scoreTag2		= null;
	JLabel			jlb_scoreTag3		= null;
	JLabel			jlb_scoreTag4		= null;
	JLabel			jlb_totalscoreTag1	= null;
	JLabel			jlb_totalscoreTag2	= null;
	JLabel			jlb_totalscoreTag3	= null;
	JLabel			jlb_totalscoreTag4	= null;
	JLabel[]		jlb_nicknames		= null;
	// tag - "현재점수", "누적점수" non-tag - int
	JScrollPane		jsp_chat			= null;
	JTextField		jtf_chat			= null;
	JTextField		jtf_thick			= null;
	JTextArea		jta_log				= null;
	JPanel			canvas				= null;
	JTable			jtb_rank			= null;
	MainNorthLeft	mnl					= null;
	ClientView		clientView			= null;

	// 그림그리기 구현을 위한 선언
	Graphics		graphics			= null;
	Graphics2D		g					= null;
	JColorChooser	colorChooser		= null;
	// 테스트용 변수
	Color			selectedColor		= null;
	////////////
	int				startX				= 0;
	int				startY				= 0;
	int				endX				= 0;
	int				endY				= 0;

	Clip			clip				= null;

	int				width				= 1600;
	int				height				= 1000;

	public GamePanel(ClientView clientView) {
		this.clientView = clientView;
		initDisplay();
	}

	public void initDisplay() {

		mnl = new MainNorthLeft(this);

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
		jbtn_modeP = new JButton("펜모드");
		jbtn_cPick = new JButton("색깔선택");
		jbtn_modeE = new JButton("지우개모드");
		jbtn_eraseAll = new JButton("전체지우기");
		jbtn_thick = new JButton("굵기 ▶▶");
		JButton[] jbtns_tool = { jbtn_modeP, jbtn_cPick, jbtn_modeE, jbtn_eraseAll, jbtn_thick };
		jlb_nicknames = new JLabel[4];
		jlb_nicknames[0] = jlb_nickName1;
		jlb_nicknames[1] = jlb_nickName2;
		jlb_nicknames[2] = jlb_nickName3;
		jlb_nicknames[3] = jlb_nickName4;
		jlb_scoreTag1 = new JLabel("현재점수");
		jlb_scoreTag2 = new JLabel("현재점수");
		jlb_scoreTag3 = new JLabel("현재점수");
		jlb_scoreTag4 = new JLabel("현재점수");
		jlb_totalscoreTag1 = new JLabel("누적점수");
		jlb_totalscoreTag2 = new JLabel("누적점수");
		jlb_totalscoreTag3 = new JLabel("누적점수");
		jlb_totalscoreTag4 = new JLabel("누적점수");
		users.add(jlb_user1);
		users.add(jlb_user2);
		users.add(jlb_user3);
		users.add(jlb_user4);
		JLabel[] jlbs_ready = { jlb_isReady1, jlb_isReady2, jlb_isReady3, jlb_isReady4 };

		// 버튼 디자인 설정
		for (JButton jbtn : jbtns_tool) {
			jbtn.setBorder(new BevelBorder(BevelBorder.RAISED));
			jbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			jbtn.setFont(new Font("휴먼모음T", Font.PLAIN, 27));
			jbtn.addActionListener(clientView);
//			jbtn.setToolTipText("툴팁텍스트");
		}
		jbtn_thick.setEnabled(false);
		jbtn_thick.setBorder(new EmptyBorder(getInsets()));
		jbtn_thick.setBorder(new EmptyBorder(getInsets()));

//		jlb_nickName1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_nickName4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_scoreTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscoreTag1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscoreTag2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscoreTag3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscoreTag4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_score4.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscore1.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscore2.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscore3.setBorder(new BevelBorder(BevelBorder.RAISED));
//		jlb_totalscore4.setBorder(new BevelBorder(BevelBorder.RAISED));
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
			jlb.setBackground(Color.white);
			jlb.setOpaque(true);
			jlb.setHorizontalAlignment(JLabel.CENTER);
			jlb.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		}
		// 닉네임 - 현재점수 - 누적점수 - ready
		for (JLabel[] jlb : users) {
			jlb[1].setOpaque(true);
			jlb[2].setOpaque(true);
			jlb[1].setHorizontalAlignment(JLabel.CENTER);
			jlb[2].setHorizontalAlignment(JLabel.CENTER);
			jlb[1].setBackground(Color.LIGHT_GRAY);
			jlb[2].setBackground(Color.LIGHT_GRAY);
		}

		jtf_chat = new JTextField(63);
		jtf_thick = new JTextField(2);

		jta_log = new JTextArea();
		jsp_chat = new JScrollPane(jta_log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		canvas = new JPanel();
		canvas.setBackground(Color.white);
		canvas.addMouseListener(clientView);
		canvas.addMouseMotionListener(clientView);

		jbtn_insert = new JButton("입력");

		jp_south.setLayout(new GridLayout(1, 2, 5, 0));
		jp_south.setPreferredSize(new Dimension(1600, 300)); ////////////////////////////////////////
		jp_chatANDlog.setLayout(new BorderLayout());

//		jp_sChat.setPreferredSize(new Dimension(800, 380));
//		jta_log.setPreferredSize(new Dimension(800, 250));
		jta_log.setEditable(false);
		jta_log.setBackground(Color.white);
		jtf_chat.setPreferredSize(new Dimension(900, 30));
		jp_sChat.add(jtf_chat);
		jbtn_insert.setPreferredSize(new Dimension(80, 30));
		jbtn_insert.addActionListener(clientView);
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
		jp_users.setPreferredSize(new Dimension(780, 250)); ////////////////////////////

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
		for (JLabel jlb : jlb_nicknames) {
			jlb.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
			jlb.setHorizontalAlignment(JLabel.CENTER);
			jlb.setOpaque(true);
//			jlb.setBackground(Color.white);
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
		jlb_totalscoreTag1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscoreTag2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscoreTag3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscoreTag4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscore1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscore2.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscore3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		jlb_totalscore4.setFont(new Font("휴먼모음T", Font.PLAIN, 30));

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
		jp_user1.add(jlb_totalscoreTag1);
		jp_user2.add(jlb_totalscoreTag2);
		jp_user3.add(jlb_totalscoreTag3);
		jp_user4.add(jlb_totalscoreTag4);
		jp_user1.add(jlb_totalscore1);
		jp_user2.add(jlb_totalscore2);
		jp_user3.add(jlb_totalscore3);
		jp_user4.add(jlb_totalscore4);

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

		// 화면을 모두 구성한 뒤 초기화 해야 함 -- 첫화면이 아니라서 그래픽이 올라갈 컴포넌트가 render 되지 않는 상태이고, 그러므로
		// (GamePanel의 생성시점에는)graphics는 null이 되어버린다.
//		graphics = getGraphics();
//		System.out.println(graphics);
//		g = (Graphics2D) graphics;
//		System.out.println(g);
//		g.setColor(Color.blue);

		/////////////////
//		JFrame jf = new JFrame();
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.add(this);
//		jf.setResizable(false);
//		jf.setVisible(true);
//		jf.setSize(1600, 1000);
//		jf.setLocationRelativeTo(null);

	}

	public void setResizeFont(JLabel label) {
		int size = label.getText().length();
		label.setFont(label.getFont().deriveFont((float) 120 / size));
	}

	public void bgm() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src\\eunTest\\kosmo_nore.wav"));
			clip = AudioSystem.getClip();
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

//	public static void main(String[] args) {
//		GamePanel c = new GamePanel();
//	}

}