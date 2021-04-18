package eunTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RankView extends JPanel {

	// "랭킹" 라벨 선언
	JPanel				jp_north			= null;
	JPanel				jp_south			= null;
	JPanel				jp_south_north		= null;
	JPanel				jp_south_south		= null;
	JLabel				jlb_ranking			= null;
	JLabel				jlb_nickname		= null;
	JLabel				jlb_nickname_data	= null;
	JLabel				jlb_totalscore		= null;
	JLabel				jlb_totalscore_data	= null;
	JScrollPane			jsp					= null;
	JTable				jtb					= null;
	DefaultTableModel	dtm					= null;
	JButton				jbtn_back			= null;
	JButton				jbtn_exit			= null;
	String[]			cols				= { "순위", "닉네임", "누적점수" };
	
	ClientView clientView = null;

	public RankView(ClientView clientView) {
		this.clientView = clientView;
		initDisplay();
	}

	public void initDisplay() {

//		JFrame jf = new JFrame();

		jp_north				= new JPanel();
		jp_south				= new JPanel();
		jp_south_north			= new JPanel();
		jp_south_south			= new JPanel();
		
		this.setLayout(new BorderLayout());
		jp_south.setLayout(new BorderLayout());
		
		jlb_ranking 			= new JLabel("전 체 랭 킹");
		jlb_nickname			= new JLabel("닉네임: ");
		jlb_nickname_data		= new JLabel("값을 불러와야 합니다.");
		jlb_totalscore			= new JLabel("누적점수: ");
		jlb_totalscore_data		= new JLabel("값을 불러와야 합니다.");
		
		dtm 					= new DefaultTableModel(new String[0][3], cols);
		jtb 					= new JTable(dtm);
		jsp 					= new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jbtn_back 				= new JButton("돌아가기");
		jbtn_exit 				= new JButton("종료");
		jtb.getTableHeader().setReorderingAllowed(false);
		jtb.setPreferredSize(new Dimension(500, 800));
		jtb.setOpaque(true);
		jtb.setBackground(Color.white);
		jlb_ranking.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		jlb_nickname.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jlb_nickname_data.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		jlb_totalscore.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jlb_totalscore_data.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		jp_north.add(jlb_ranking);
		jp_south_south.add(jbtn_back);
		jp_south_south.add(jbtn_exit);
		jp_south_north.add(jlb_nickname);
		jp_south_north.add(jlb_nickname_data);
		jp_south_north.add(jlb_totalscore);
		jp_south_north.add(jlb_totalscore_data);
		jp_south.add("North", jp_south_north);
		jp_south.add("South", jp_south_south);

		this.add("North", jp_north);
		this.add("Center", jsp);
		this.add("South", jp_south);

//		jf.add("Center", this);
//		jf.setSize(500, 800);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setVisible(true);
	}

//	public static void main(String[] args) {
//		new RankView();
//	}

}
