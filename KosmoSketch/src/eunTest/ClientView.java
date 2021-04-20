package eunTest;

import java.awt.Event;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import test.project1.Protocol;

public class ClientView extends JFrame implements ActionListener, KeyListener {

	LoginView				login			= null;
	JoinView				join			= null;
	WaitRoomView			waitRoom		= null;
	GamePanel				game			= null;
	RankView				rank			= null;
	WaitRoomClientThread	thread_waitRoom	= null;

	public ClientView() {
		// 화면에 붙일 패널 생성
		login = new LoginView(this);
		join = new JoinView(this);
		waitRoom = new WaitRoomView(this);
		game = new GamePanel(this);
		rank = new RankView(this);
		thread_waitRoom = new WaitRoomClientThread(this);
		this.add("Center", login);
		this.setSize(login.width, login.height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ClientView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == login.jbtn_join) {
			this.remove(login);
			this.setSize(join.width, join.height);
			this.repaint();
			this.add("Center", join);
			this.revalidate();
		} else if (obj == login.jbtn_login || obj == login.jtf_id || obj == login.jpf_pw) {
			System.out.println(login.jtf_id.getText());
			System.out.println(login.jpf_pw.getText());
			if (login.jtf_id.getText().length() != 0 && login.jpf_pw.getText().length() != 0) {
				String	id	= login.jtf_id.getText();
				String	pw	= login.jpf_pw.getText();
				System.out.println(1111);
				System.out.println(thread_waitRoom.client);
				try {
					thread_waitRoom.oos.writeObject(id + Protocol._CUT + pw);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 입력되지 않았습니다.");
			}
		} else if (obj == join.jbtn_back) {
			this.remove(join);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.revalidate();
		} else if (obj == waitRoom.jbtn_logout) {
			this.remove(waitRoom);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.revalidate();
		} else if (obj == waitRoom.jbtn_exit) {
			try {
				System.out.println(thread_waitRoom);
				System.out.println(thread_waitRoom.oos);
				thread_waitRoom.oos.writeObject(Protocol._EXIT + " ");////////////////////////////////////
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
	// jtf_id 와 jpf_pw 에서 TAB키를 눌렀을 때, 포커스가 이동하고 입력된 텍스트가 전체선택되도록 설정.
	@Override
	public void keyPressed(KeyEvent e) {
		if(login.jtf_id.isFocusOwner() && e.getKeyCode() == KeyEvent.VK_TAB) {
			login.jpf_pw.grabFocus();
			login.jpf_pw.selectAll();
	} else if (login.jpf_pw.isFocusOwner() && e.getKeyCode() == KeyEvent.VK_TAB){
			login.jtf_id.grabFocus();
			login.jtf_id.selectAll();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
