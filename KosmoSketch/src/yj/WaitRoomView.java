package yj;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WaitRoomView extends JPanel {

	JPanel jp_south = null;
	JPanel jp_south_left = null;
	JPanel jp_south_right = null;
	JLabel jlb_logo = null;
	String[] cols = {"방번호", "방이름", "인원", "상태"};
	DefaultTableModel dtm_room = null;
	JTable jtb_room = null;
	JScrollPane jsp_room = null;
	JButton jbtn_logout = null;
	JButton jbtn_exit = null;
	JButton jbtn_createRoom = null;
//	Vector roomlist = new Vector();
	ClientView clientView = null;
	
	int width = 1600;
	int height = 1000;
	
	public WaitRoomView(ClientView clientView) {
		this.clientView = clientView;
		initDisplay();
	}
	
	public void initDisplay() {
		// 생성
		jp_south = new JPanel();
		jp_south_left = new JPanel();
		jp_south_right = new JPanel();
		jlb_logo = new JLabel("KosmoCatch");
		// 임시로 3 넣었음.
		dtm_room = new DefaultTableModel(new Object[3][4], cols);
		jtb_room = new JTable(dtm_room);
		jsp_room = new JScrollPane(jtb_room, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
										   , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jbtn_logout = new JButton("로그아웃");
		jbtn_exit = new JButton("종료");
		jbtn_createRoom = new JButton("방만들기");
				
		// 상세설정
		jbtn_logout.addActionListener(clientView);
		jbtn_exit.addActionListener(clientView);
		jbtn_createRoom.addActionListener(clientView);
		jlb_logo.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		jlb_logo.setHorizontalAlignment(JLabel.CENTER);
		
		// add 하자.
		jp_south_left.setAlignmentX(LEFT_ALIGNMENT);
		jp_south_right.setAlignmentX(RIGHT_ALIGNMENT);
		jp_south_left.add(jbtn_logout);
		jp_south_left.add(jbtn_exit);
		jp_south_right.add(jbtn_createRoom);
		jp_south.setLayout(new BorderLayout());
		jp_south.add("West", jp_south_left);
		jp_south.add("East", jp_south_right);
		this.setLayout(new BorderLayout());
		this.add("North", jlb_logo);
		this.add("Center", jsp_room);
		this.add("South", jp_south);
		
//		JFrame jf = new JFrame();
//		jf.add("Center", this);
//		
//		// JFrame 관련 설정
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setResizable(false);
//		jf.setVisible(true);
//		jf.setSize(1600, 1000);
//		jf.setVisible(true);
	}
	
//	public static void main(String[] args) {
//		new WaitRoomView();
//	}

}
