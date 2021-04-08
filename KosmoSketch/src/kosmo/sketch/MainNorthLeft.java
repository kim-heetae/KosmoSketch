package kosmo.sketch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainNorthLeft extends JFrame {

	//선언부
	JPanel jp_north_left = null;
	JPanel jp_for_logo = null;
	JPanel jp_for_table = null;
	JPanel jp_for_button = null;
	JLabel jlb_logo = null;
	JLabel jlb_timer = null;
	JLabel jlb_ranking = null;
	JButton jbtn_ready = null;
	JButton jbtn_exit = null;
	DefaultTableModel dtm_ranking = null;
	JTable jtb_ranking = null;
	JScrollPane jsp_ranking = null;
	String[] cols_ranking = {"순위", "닉네임", "점수"};
	
	
	public MainNorthLeft() {
		initDisplay();
	}
	
	public void initDisplay() {
		
		jp_north_left = new JPanel();
		jp_for_logo = new JPanel();
		jp_for_table = new JPanel();
		jp_for_button = new JPanel();
		jlb_logo = new JLabel("KosmoCatch");
		jlb_timer = new JLabel("00:00:00");
		jlb_ranking = new JLabel("현재 게임의 RANKING");
		jbtn_ready = new JButton("게임준비");
		jbtn_exit = new JButton("나가기");
		dtm_ranking = new DefaultTableModel(new String[0][3], cols_ranking);
		jtb_ranking = new JTable(dtm_ranking);
		jsp_ranking = new JScrollPane(jtb_ranking);
		
		jbtn_ready.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[게임준비] 버튼 호출 완료");
			}
		});
		jbtn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[나가기] 버튼 호출 완료");
			}
		});
		
		jlb_logo.setHorizontalAlignment(JLabel.CENTER);
		jlb_timer.setHorizontalAlignment(JLabel.CENTER);
		jlb_ranking.setHorizontalAlignment(JLabel.CENTER);
		jlb_logo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 35));
		jlb_timer.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jlb_ranking.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		jlb_ranking.setOpaque(true);
		jp_for_logo.setBackground(Color.yellow);
		jlb_ranking.setBackground(Color.orange);
		jtb_ranking.getTableHeader().setReorderingAllowed(false);
		
		jp_north_left.setLayout(new BorderLayout());
		jp_for_logo.setLayout(new BorderLayout());
		jp_for_table.setLayout(new BorderLayout());
		jp_for_logo.add("Center", jlb_logo);
		jp_for_logo.add("South", jlb_timer);
		jp_for_table.add("North", jlb_ranking);
		jp_for_table.add("Center", jsp_ranking);
		jp_for_button.add(jbtn_ready);
		jp_for_button.add(jbtn_exit);
		jp_north_left.add("North", jp_for_logo);
		jp_north_left.add("Center", jp_for_table);
		jp_north_left.add("South", jp_for_button);
		
		
		
		this.add("Center", jp_north_left);
		this.setSize(300, 600);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new MainNorthLeft();
	}

}
