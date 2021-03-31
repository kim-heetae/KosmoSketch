package trash.can;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class CatchFrame extends JFrame {
   JPanel jp_north, jp_modes, jp_chat, jp_users;
   
   JLabel jlb_tag, jlb_time
       , jlb_user1, jlb_user2, jlb_user3, jlb_user4
       , jlb_penM, jlb_eraseM, jlb_colors, jlb_thick, jlb_eraseAll, jlb_send;
   
   JTextArea jta_log; //채팅로그 - editable false
   
   JTextField jtf_chat, jtf_thick;
   
   JButton jbtn_exit;
   
   JScrollPane jsp;
   
   Canvas canvas; //그림판   paint(Graphic g) 메소드 만들어주기
   
   public void initDisplay(){
      jp_north = new JPanel();
      jp_modes = new JPanel();
      jp_chat  = new JPanel();
      jp_users = new JPanel();
      
      jlb_tag = new JLabel("KosmoCatch");
      jlb_time = new JLabel("00:00");
      jbtn_exit = new JButton("x");
      
      jlb_user1 = new JLabel("1번");
      jlb_user2 = new JLabel("2번");
      jlb_user3 = new JLabel("3번");
      jlb_user4 = new JLabel("4번");
      
      jlb_penM   = new JLabel("펜모드");
      jlb_eraseM = new JLabel("지우개모드");
      jlb_colors = new JLabel("색깔고르기");//colorPicker 호출시켜주기
      jlb_thick   = new JLabel("굵기");
      jlb_eraseAll = new JLabel("전체지우기");
      
      jlb_send = new JLabel("전송");
      
      jta_log = new JTextArea();
      
      jtf_chat = new JTextField();
      jtf_thick = new JTextField();
      
      canvas = new Canvas();
      
      Font font = new Font("Gothic", Font.BOLD, 28);
      BevelBorder border = new BevelBorder(BevelBorder.RAISED);
      ///////////////////////////////////////////////////////////////////////////////////
      this.setLayout(null);
      jp_north.setLayout(null);
      jlb_tag.setBounds(10, 10, 200, 50);
      jlb_tag.setFont(font);
      jlb_tag.setBorder(border);
      jp_north.add(jlb_tag);
      
      jlb_time.setBounds(125, 70, 85, 50);
      jlb_time.setFont(font);
      jlb_time.setBorder(border);
      jp_north.add(jlb_time);
      
      jbtn_exit.setBounds(1500, 10, 50, 50);
      jbtn_exit.setFont(font);
      jp_north.add(jbtn_exit);
      
      canvas.setBounds(210, 70, 1350, 680);
      //캔버스 머로 그릴지 정해요
      jp_north.add(canvas);
      
      jp_north.setBorder(border);
      jp_north.setBounds(5, 1, 1575, 690); //x = fullSize, 
      this.add(jp_north);
      /////////////////////////////////////////////////////////////////////////////////////
      jp_modes.setLayout(null);
      jlb_penM.setBounds(5,1,100,50);
      jlb_penM.setBorder(border);
      jlb_penM.setFont(font);
      jp_modes.add(jlb_penM);
      
      jlb_eraseM.setBounds(117,1,170,50);
      jlb_eraseM.setBorder(border);
      jlb_eraseM.setFont(font);
      jp_modes.add(jlb_eraseM);
      
      jlb_colors.setBounds(295,1,170,50);
      jlb_colors.setBorder(border);
      jlb_colors.setFont(font);
      jp_modes.add(jlb_colors);
      
      jlb_thick.setBounds(475,1,75,50);
      jlb_thick.setBorder(border);
      jlb_thick.setFont(font);
      jp_modes.add(jlb_thick);
      
      jtf_thick.setBounds(558,2,50,50);
      jtf_thick.setFont(font);
      jp_modes.add(jtf_thick);
      
      
      jlb_eraseAll.setBounds(615,1,170,50);
      jlb_eraseAll.setBorder(border);
      jlb_eraseAll.setFont(font);
      jp_modes.add(jlb_eraseAll);
      
      jp_modes.setBorder(border);
      jp_modes.setBounds(784,700, 795, 55);////x = fullSize, y= 
      this.add(jp_modes);
      ///////////////////////////////////////////////////////////////////////////////////////
      
      jp_chat.setLayout(null);
      jta_log.setBounds(5,5,765, 196);
      jta_log.setBorder(border);
      jp_chat.add(jta_log);
      
      jtf_chat.setBounds(5,208,708, 40);
      jtf_chat.setBorder(border);
      jtf_chat.setFont(font);
      jp_chat.add(jtf_chat);
      
      jlb_send.setBounds(729, 208, 40, 40);
      jlb_send.setBorder(border);
      jp_chat.add(jlb_send);
      
      jp_chat.setBorder(border);
      jp_chat.setBounds(5,700,775, 255);
      this.add(jp_chat);
      /////////////////////////////////////////////////////////////////////////////////////////
      
      jp_users.setLayout(null);
      
      jlb_user1.setBounds(7,13,388,80);
      jlb_user1.setFont(font);
      jlb_user1.setBorder(border);
      jp_users.add(jlb_user1);
      
      jlb_user2.setBounds(397, 13, 388, 80);
      jlb_user2.setFont(font);
      jlb_user2.setBorder(border);
      jp_users.add(jlb_user2);
      
      jlb_user3.setBounds(7, 105, 388, 80);
      jlb_user3.setFont(font);
      jlb_user3.setBorder(border);
      jp_users.add(jlb_user3);
      
      jlb_user4.setBounds(397, 105, 388, 80);
      jlb_user4.setFont(font);
      jlb_user4.setBorder(border);
      jp_users.add(jlb_user4);
      
      jp_users.setBorder(border);
      jp_users.setBounds(785, 760, 793,195);
      this.add(jp_users);
      ////////////////////////////////////////////////////////////////////////////////////////
      
      this.setSize(1600,1000);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   
   public static void main(String[] args) {
      CatchFrame c = new CatchFrame();
      c.initDisplay();
   }

}