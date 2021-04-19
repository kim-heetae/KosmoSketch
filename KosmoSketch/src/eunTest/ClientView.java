package eunTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import test.project1.Protocol;

public class ClientView extends JFrame implements ActionListener {

	LoginPanel				login			= null;
	JoinView				join			= null;
	WaitRoomView			waitRoom		= null;
	GamePanel				game			= null;
	RankView				rank			= null;
	WaitRoomClientThread	thread_waitRoom	= null;

	public ClientView() {
		// 화면에 붙일 패널 생성
		login			= new LoginPanel(this);
		join			= new JoinView(this);
		waitRoom		= new WaitRoomView(this);
		game			= new GamePanel(this);
		rank			= new RankView(this);
		thread_waitRoom	= new WaitRoomClientThread(this);
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
		}
		else if (obj == login.jbtn_login) {
			String	id	= login.jtf_id.getText();
			String	pw	= login.jtf_pw.getText();
			System.out.println(1111);
			System.out.println(thread_waitRoom.client);
			try {
				thread_waitRoom.oos.writeObject(id + Protocol._CUT + pw);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (obj == join.jbtn_back) {
			this.remove(join);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.revalidate();
		}
		else if (obj == waitRoom.jbtn_logout) {
			this.remove(waitRoom);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.revalidate();
		}
		else if (obj == waitRoom.jbtn_exit) {
			try {
				System.out.println(thread_waitRoom);
				System.out.println(thread_waitRoom.oos);
				thread_waitRoom.oos.writeObject(Protocol._EXIT + "");////////////////////////////////////
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

}
