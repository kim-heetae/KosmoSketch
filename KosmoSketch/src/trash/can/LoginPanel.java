package trash.can;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class LoginPanel extends JPanel implements ActionListener{
	JPanel jp_north, jp_center, jp_south, jp_east, jp_west, jp_cWest, jp_cCenter;
	
	JLabel jlb_logo, jlb_id, jlb_pw;
	
	JTextField jtf_id, jtf_pw;
	
	JButton jbtn_login, jbtn_join, jbtn_exit;
	
	String imgPath = "src\\trash\\can\\";
	ImageIcon ig 		= new ImageIcon(imgPath+"BONOBONO.jpg");
	
	class BONOBO extends JPanel{
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	}
	
	public LoginPanel() {
		JFrame jf = new JFrame();
		jf.setContentPane(new BONOBO());
////////////////////////////////////테스트용 프레임///////////////////////////////////
		this.setLayout(new BorderLayout());
		
		//north
		jp_north = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jlb_logo = new JLabel("Kosom");
		jlb_logo.setFont(new Font("Dialog", Font.BOLD, 30));
		jp_north.add(jlb_logo);
		jp_north.setPreferredSize(new Dimension(400, 160));
		jp_north.setOpaque(false);
//		jp_north.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add("North", jp_north);
		
		//center
		jp_center = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp_cWest = new JPanel(new GridLayout(2,1,10,10));
		jp_cCenter = new JPanel(new GridLayout(2,1,10,10));
			//center-west
		jlb_id = new JLabel("ID : ");
		jlb_pw = new JLabel("PW : ");
		jp_cWest.add(jlb_id);
		jp_cWest.add(jlb_pw);
		jp_center.add("West", jp_cWest);
			//center-center
		jtf_id = new JTextField(12);
		jtf_pw = new JTextField(12);		
		jp_cCenter.add(jtf_id);
		jp_cCenter.add(jtf_pw);
		jp_center.add("Center", jp_cCenter);
		jp_center.setOpaque(false);
//		jp_center.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add("Center", jp_center);
		
		//south
		jp_south = new JPanel(new FlowLayout());
		jbtn_join = new JButton("회원가입");
		jbtn_exit = new JButton("나가요");
		jp_south.add(jbtn_join);
		jp_south.add(jbtn_exit);
		jp_south.setOpaque(false);
//		jp_south.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add("South", jp_south);
		
		//east
		
		jp_east = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp_east.setPreferredSize(new Dimension(220,200));
		jbtn_login = new JButton("Login");
		jbtn_login.setPreferredSize(new Dimension(80,60));
		jp_east.add(jbtn_login);
		jp_east.setOpaque(false);
//		jp_east.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add("East", jp_east);
		
		this.setOpaque(false);
		//testFrame
		jf.add(this);
		jf.setSize(750,550);
		jf.setVisible(true);
	}
	
	
	
	
	
	public static void main(String [] args) {
		new LoginPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
