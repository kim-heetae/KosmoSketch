package eunTest;

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
	WaitRoomClientThread	clientThread	= null;
	
	String					input_ID		= null;
	String					input_Nickname	= null;
	boolean					isMatchCode		= false;

	public ClientView() {
		// 화면에 붙일 패널 생성
		login = new LoginView(this);
		join = new JoinView(this);
		waitRoom = new WaitRoomView(this);
		game = new GamePanel(this);
		rank = new RankView(this);
		// 클라이언트 스레드 생성
		clientThread = new WaitRoomClientThread(this);
		
		// 프레임 세팅
		this.setTitle("로그인");
		this.add("Center", login);
		this.setSize(login.width, login.height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ClientView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == login.jbtn_join) {
			join = new JoinView(this);
			this.remove(login);
			this.setSize(join.width, join.height);
			this.repaint();
			this.add("Center", join);
			this.setTitle("회원가입");
			this.revalidate();
		} else if (obj == login.jbtn_login || obj == login.jtf_id || obj == login.jpf_pw) {
			System.out.println(login.jtf_id.getText());
			System.out.println(login.jpf_pw.getText());
			if (login.jtf_id.getText().length() != 0 && login.jpf_pw.getText().length() != 0) {
				String	id	= login.jtf_id.getText();
				String	pw	= login.jpf_pw.getText();
				try {
					clientThread.oos.writeObject(Protocol._LOGIN + Protocol._CUT + id + Protocol._CUT + pw);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 입력되지 않았습니다.");
			}
		// 아이디 중복확인 버튼을 눌렀을 때의 이벤트
		} else if (obj == join.jbtn_id) {
			input_ID = join.jtf_id.getText();
			try {
				clientThread.oos.writeObject(Protocol._CHECK_ID + Protocol._CUT + input_ID);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		// 닉네임 중복확인 버튼을 눌렀을 때의 이벤트
		} else if (obj == join.jbtn_nickname) {
			input_Nickname = join.jtf_nickname.getText();
			try {
				clientThread.oos.writeObject(Protocol._CHECK_NICKNAME + Protocol._CUT + input_Nickname);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (obj == join.jbtn_email) {
			String email = join.jtf_email.getText();
			try {
				clientThread.oos.writeObject(Protocol._SEND_EMAIL + Protocol._CUT + email);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (obj == join.jbtn_code) {
			String inputCode = join.jtf_code.getText();
			try {
				clientThread.oos.writeObject(Protocol._CHECK_CODE + Protocol._CUT + inputCode);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		// 뒤로가기 버튼을 눌렀을 때의 이벤트
		} else if (obj == join.jbtn_back) {
			login = new LoginView(this);
			this.remove(join);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.setTitle("로그인");
			this.revalidate();
		// 회원가입 버튼을 눌렀을 때의 이벤트
		} else if (obj == join.jbtn_join) {
			if(join.jpf_pw.getText().equals(join.jpf_pw_confirm.getText()) && isMatchCode){
				try {
					clientThread.oos.writeObject(Protocol._JOIN + Protocol._CUT + join.jpf_pw.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}
		} else if (obj == waitRoom.jbtn_logout) {
			login = new LoginView(this);
			this.remove(waitRoom);
			this.setSize(login.width, login.height);
			this.repaint();
			this.add("Center", login);
			this.setTitle("로그인");
			this.revalidate();
		} else if (obj == waitRoom.jbtn_exit) {
			try {
				clientThread.oos.writeObject(Protocol._EXIT + " ");////////////////////////////////////
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
