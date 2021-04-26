package eunTest;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import test.project1.Protocol;

public class ClientView extends JFrame implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	LoginView				login				= null;
	JoinView				join				= null;
	WaitRoomView			waitRoom			= null;
	GamePanel				game				= null;
	RankView				rank				= null;
	WaitRoomClientThread	clientThread		= null;
	ChatClientThread		chatCilentThread	= null;
	TimerClientThread		timerCilentThread	= null;
	PaintClientThread		paintCilentThread	= null;
	GameClientThread		gameCilentThread	= null;

	String					input_ID			= null;
	String					input_Nickname		= null;
	String					myNickname			= null;
	boolean					isMatchCode			= false;
	Vector<String>			oneRoom				= null;
	List<Vector<String>>	roomList			= null;
	int						roomNum				= 0;
	int						totalScore			= 0;

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
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
			if (join.jpf_pw.getText().equals(join.jpf_pw_confirm.getText()) && isMatchCode) {
				try {
					clientThread.oos.writeObject(Protocol._JOIN + Protocol._CUT + join.jtf_id.getText() + Protocol._CUT
							+ join.jpf_pw.getText() + Protocol._CUT + join.jtf_nickname.getText() + Protocol._CUT
							+ join.jtf_email.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}
		} else if (obj == waitRoom.jbtn_logout) {
			// 로그아웃함을 서버에 알려서 리스트에서 지울 수 있도록 함.
			try {
				clientThread.oos.writeObject(String.valueOf(Protocol._LOGOUT));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (obj == waitRoom.jbtn_exit || obj == login.jbtn_exit) {
			try {
				clientThread.oos.writeObject(String.valueOf(Protocol._EXIT));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
		// [방만들기] 버튼을 눌렀을 때의 이벤트 구현 //////////////////////////////구현에만 목적을 두었으니 시간 나면
		// 정리해봅시다...
		else if (obj == waitRoom.jbtn_createRoom) {
			JDialog		jd			= new JDialog();
			JButton		ok			= new JButton("확인");
			JTextField	jtf_title	= new JTextField(20);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jd.setVisible(false);
					String roomName = jtf_title.getText();
					if (roomName != null && !"".equals(roomName) && roomName.length() != 0) {
						try {
							clientThread.oos.writeObject(Protocol._MAKEROOM + Protocol._CUT + roomName);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(jd, "아무것도 입력하지 않았습니다.");
					}
				}
			});
			jtf_title.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jd.setVisible(false);
					String roomName = jtf_title.getText();
					if (roomName != null && !"".equals(roomName) && roomName.length() != 0) {
						try {
							clientThread.oos.writeObject(Protocol._MAKEROOM + Protocol._CUT + roomName);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(jd, "아무것도 입력하지 않았습니다.");
					}
				}
			});
			jd.setLayout(new FlowLayout());
			jd.add(jtf_title);
			jd.add(ok);
			jd.setTitle("방제목을 입력하세요.");
			jd.setResizable(false);
			jd.setSize(300, 100);
			jd.setVisible(true);
			jd.setLocationRelativeTo(waitRoom);
//			String roomName = JOptionPane.showInputDialog(this, "방 제목을 입력하세요.");
//			if (roomName != null) {
//				if (roomName.equals("") && roomName.length() != 0) {
//					int iroomName = Integer.parseInt(roomName);
//					if (iroomName == 1) { // 취소버튼 클릭 시
//						System.out.println("취소버튼 호출 완료");
//					}
//				} else {
//					int iroomName = Integer.parseInt(roomName);
//					if (iroomName == 1) { // 취소버튼 클릭 시
//						System.out.println("취소버튼 호출 완료");
//					} else if (iroomName == 2) { // 확인버튼 클릭 시
//						try {
//							System.out.println("여기호출~~~");
//							clientThread.oos.writeObject(Protocol._MAKEROOM + Protocol._CUT + roomName);
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//					}
//					System.out.println("널은 아닌데 빈문자열도 아님");
//				}
//			}

//			if (roomName == null || roomName.equals("") || roomName.length() == 0) {
//			} else {
//
//
//			}

//			System.out.println(roomName);
//			if(Integer.parseInt(roomName.toString())==JOptionPane.OK_OPTION) {
//				if (roomName == null || roomName.equals("") || roomName.toString().length() == 0) {
//				} else {
//					try {
//						clientThread.oos.writeObject(Protocol._MAKEROOM + Protocol._CUT + roomName);
//					}
//					catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
			// 더 추가/수정
			// 해야함///////////////////////////////////////////////////////////////////
		} else if (obj == game.jbtn_cPick) {
			game.g.setColor(JColorChooser.showDialog(this, "Color", Color.black));
			// 게임패널에서 [전체 지우기] 버튼을 눌렀을 때의 이벤트
		} else if (obj == game.jbtn_eraseAll) {
			game.canvas.repaint();
			// 게임패널(-mnl패널)에서 나가기 버튼 눌렀을 때의 이벤트: 대기실로 이동
		} else if (obj == game.mnl.jbtn_exit) {
			try {
				clientThread.oos.writeObject(String.valueOf(Protocol._ROOMOUT));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.remove(this.game);
			this.setSize(this.waitRoom.width, this.waitRoom.height);
			this.setTitle(myNickname + "님의 창");
			this.repaint();
			this.add("Center", this.waitRoom);
			this.revalidate();

		} else if (obj == game.mnl.jbtn_ready) {
//			if(game.)
			try {
				gameCilentThread.oos.writeObject(Protocol._READY + Protocol._CUT + myNickname);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//		} else if () {
//		} else if () {
//		} else if () {
//		} else if () {
		}
	}

	// jtf_id 와 jpf_pw 에서 TAB키를 눌렀을 때, 포커스가 이동하고 입력된 텍스트가 전체선택되도록 설정.
	@Override
	public void keyPressed(KeyEvent e) {
		if (login.jtf_id.isFocusOwner() && e.getKeyCode() == KeyEvent.VK_TAB) {
			login.jpf_pw.grabFocus();
			login.jpf_pw.selectAll();
		} else if (login.jpf_pw.isFocusOwner() && e.getKeyCode() == KeyEvent.VK_TAB) {
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == waitRoom.jtb_room && e.getClickCount() == 2) {
			System.out.println("clientView - waitRoomView 더블클릭됨");
			int selectedRow = waitRoom.jtb_room.getSelectedRow();
			roomNum = Integer.parseInt(waitRoom.dtm_room.getValueAt(selectedRow, 0).toString());
			System.out.println(selectedRow);
			try {
				clientThread.oos.writeObject(Protocol._ROOMIN + Protocol._CUT + roomNum);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			waitRoom.jtb_room.
//			waitRoom.jtb_room.getSelectedRow()
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == game.canvas) {
			game.startX = e.getX();
			game.startY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == game.canvas) {
			game.endX = e.getX();
			game.endY = e.getY();
			game.g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, 0)); // 선굵기
			game.g.drawLine(game.startX, game.startY, game.endX, game.endY);
			game.startX = game.endX;
			game.startY = game.endY;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
