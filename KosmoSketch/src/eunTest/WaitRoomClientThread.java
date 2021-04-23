package eunTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import test.project1.Protocol;

public class WaitRoomClientThread extends Thread {

	String				questioner	= null;
	Socket				client		= null;
	Socket				gameSocket	= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;
	ClientView			clientView	= null;
	String				nickName	= null;
	int					totalScore	= 0;

	// 생성자
	public WaitRoomClientThread(ClientView clientView) {
		this.clientView = clientView;
		init();
		this.start();
	}

	// 소켓 생성
	public void init() {
		try {
			client = new Socket("localhost", Port._WAITROOM);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Room정보를 담는 대기실의 테이블을 새로고침하는 메소드
	public void refreshTable() {
		while (clientView.waitRoom.dtm_room.getRowCount() > 0) {
			clientView.waitRoom.dtm_room.removeRow(0);
		}
//		for(Vector<String> room : clientView.roomList) {
//			if(room)
//		}
		for (Vector<String> room : clientView.roomList) {
			room.set(0, String.valueOf(clientView.roomList.indexOf(room)+1));
		}
		for (Vector<String> room : clientView.roomList) {
			clientView.waitRoom.dtm_room.addRow(room);
		}
	}

	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		while (!isStop) {
			try {
				msg = (String) ois.readObject();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				// 프로토콜을 가져와서 담음.
				protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				// 로그인 성공 시: 서버 스레드가 닉네임을 알려줌
				case Protocol._CLIENT_INFO: // _LOGIN_SUCCESS 프로토콜은 필요 없고, 바로 _CLIENT_INFO로 정보를 주자.
					// 서버가 DB에 접근에서 가져온 [닉네임]을 받아온다.
					nickName = st.nextToken();
					String msg_isLoggedIn = st.nextToken();
					this.totalScore = Integer.parseInt(st.nextToken());
					clientView.myNickname = nickName;
					clientView.remove(clientView.login);
					clientView.setSize(clientView.waitRoom.width, clientView.waitRoom.height);
					clientView.setTitle(nickName + "님의 창");
					clientView.repaint();
					clientView.add("Center", clientView.waitRoom);
					clientView.revalidate();
					clientView.oneRoom = new Vector<>();
					clientView.roomList = new ArrayList<Vector<String>>();
					// 라벨의 초기화 //////////////////////////////////////////////////////// X
					// 왼쪽 랭킹 테이블에 입장한 순서대로(globalList의 순서대로) 붙여준다. ///////////////// X
					break;
				// 로그인 실패 시 : 메세지를 클라이언트의 화면에 띄워줌
				case Protocol._LOGIN_FAILURE:
					msg_isLoggedIn = st.nextToken();
					JOptionPane.showMessageDialog(clientView, msg_isLoggedIn);
					break;
				case Protocol._CHECK_ID:
					String msg_isDuplicated = st.nextToken();
					JOptionPane.showMessageDialog(clientView, msg_isDuplicated);
					break;
				case Protocol._CHECK_NICKNAME:
					msg_isDuplicated = st.nextToken();
					JOptionPane.showMessageDialog(clientView, msg_isDuplicated);
					break;
				case Protocol._EMAIL_FAILURE:
					JOptionPane.showMessageDialog(clientView, "잘못된 이메일입니다.");
					break;
				case Protocol._EMAIL_SUCCESS:
					JOptionPane.showMessageDialog(clientView, "인증번호가 전송되었습니다.");
					break;
				case Protocol._CODE_FAILURE:
					JOptionPane.showMessageDialog(clientView, "잘못된 인증번호입니다. 다시 확인해주세요.");
					clientView.isMatchCode = false;
					break;
				case Protocol._CODE_SUCCESS:
					JOptionPane.showMessageDialog(clientView, "확인되었습니다.");
					clientView.isMatchCode = true;
					break;
				case Protocol._JOIN_FAILURE:
					JOptionPane.showMessageDialog(clientView, "회원가입 실패!\n입력한 정보를 다시 확인해주세요.");
					break;
				case Protocol._JOIN_SUCCESS:
					JOptionPane.showMessageDialog(clientView, "회원가입되었습니다.");
					clientView.remove(clientView.join);
					clientView.setSize(clientView.login.width, clientView.login.height);
					clientView.repaint();
					clientView.add("Center", clientView.login);
					clientView.revalidate();
					break;
				// 대기실에 처음 입장했을 때 이미 생성되어 있는 정보를 받아온다.
				// 또는 방이 새로 생성되었을 때 새로고침 해준다.
				case Protocol._ROOM_INFO:
					clientView.oneRoom = null;
					clientView.oneRoom = new Vector<String>();
					clientView.oneRoom.add(st.nextToken());
					clientView.oneRoom.add(st.nextToken());
					clientView.oneRoom.add(st.nextToken() + "/4");
					boolean isGamePlay = Boolean.getBoolean(st.nextToken());
					if (isGamePlay) {
						clientView.oneRoom.add("게임중");
					} else {
						clientView.oneRoom.add("대기중");
					}
					clientView.roomList.add(clientView.oneRoom);
					refreshTable();
/////////////////////////////////////////////////단위테스트 필요////////////////////////////////////////
					break;
//				case Protocol._MAKEROOM:
//					clientView.oneRoom.add(st.nextToken());
//					clientView.oneRoom.add(st.nextToken());
//					clientView.oneRoom.add(st.nextToken() + "/4");
//					clientView.oneRoom.add(st.nextToken());
//					refreshTable();
				// 받아온 닉네임에 해당하는 클라이언트의 READY라벨 색을 변경한다. (white > orange)
				// 라벨의 텍스트도 NOT READY > READY 로 변경한다.
//					break;
				case Protocol._MAKEROOM:
					clientView.game = new GamePanel(clientView);
					clientView.setTitle(st.nextToken());
					clientView.remove(clientView.waitRoom);
					clientView.setSize(clientView.game.width, clientView.game.height);
					clientView.repaint();
					clientView.add("Center", clientView.game);
					clientView.revalidate();
					// 그림을 그리기 위해 그래픽 도구 추가
					clientView.game.graphics = clientView.game.canvas.getGraphics();
					clientView.game.g = (Graphics2D)clientView.game.graphics;
					clientView.game.g.setColor(Color.black);
					clientView.game.bgm();
					// 클라이언트가 방을 만들고 들어갈 때 라벨값 초기화
					clientView.game.jlb_nickName1.setText(this.nickName);
					clientView.game.setResizeFont(clientView.game.jlb_nickName1);
					System.out.println(this.nickName);
					
					clientView.game.jlb_totalscore1.setText(String.valueOf(this.totalScore));
					System.out.println(this.totalScore);
					break;
				case Protocol._LOGOUT:
					clientView.login = new LoginView(clientView);
					clientView.remove(clientView.waitRoom);
					clientView.setSize(clientView.login.width, clientView.login.height);
					clientView.repaint();
					clientView.add("Center", clientView.login);
					clientView.setTitle("로그인");
					clientView.revalidate();
					break;
				case Protocol._ROOM_WELCOME:
					clientView.game = new GamePanel(clientView);
					clientView.remove(clientView.waitRoom);
					clientView.setSize(clientView.game.width, clientView.game.height);
					clientView.repaint();
					clientView.add("Center", clientView.game);
					clientView.setTitle(st.nextToken());
					clientView.revalidate();
					gameSocket = new Socket("localhost", port)
					clientView.game.bgm();
					
					break;
				case Protocol._ROOM_REJECTED:
					JOptionPane.showMessageDialog(clientView, "입장가능 인원을 초과하였습니다.");
					break;
				case Protocol._ROOM_UPDATE:
					String roomnum = st.nextToken();
					clientView.oneRoom = null;
					clientView.oneRoom = new Vector<String>();
					clientView.oneRoom.add(roomnum);
					clientView.oneRoom.add(st.nextToken());
					clientView.oneRoom.add(st.nextToken() + "/4");
					isGamePlay = Boolean.getBoolean(st.nextToken());
					if (isGamePlay) {
						clientView.oneRoom.add("게임중");
					} else {
						clientView.oneRoom.add("대기중");
					}
					clientView.roomList.set(Integer.parseInt(roomnum) - 1, clientView.oneRoom);
					refreshTable();
					break;
				case Protocol._ROOMOUT:
					int roomNum = Integer.parseInt(st.nextToken());
					int room_clientNum = Integer.parseInt(st.nextToken());
					System.out.println("방번호===> "+roomNum);
					System.out.println("해당 방의 클라 수===> "+room_clientNum);
					clientView.waitRoom.dtm_room.setValueAt(room_clientNum+"/4", roomNum-1, 2);
//					clientView.
					refreshTable();
					break;
				case Protocol._CLOSEROOM:
					roomNum = Integer.parseInt(st.nextToken());
					System.out.println("방번호===> "+roomNum);
					clientView.waitRoom.dtm_room.removeRow(roomNum-1);
					clientView.roomList.remove(roomNum-1);
					refreshTable();
					break;
				case Protocol._NOT_READY:
					// 받아온 닉네임에 해당하는 클라이언트의 READY라벨 색을 변경한다. (orange > white)
					// 라벨의 텍스트도 READY > NOT READY 로 변경한다.
					break;
				case Protocol._START: // 서버쪽에서 start를 보내면서 (출제자)닉네임을 같이 보내줘야 '비활성화'를 처리할 수 있다.
					// ****(임시명)paint()를 호출하기 전에 현재 클라이언트가 '출제자'인지를 항상 if문으로 체크한다.
					// [게임준비]버튼, [나가기]버튼, [모두 지우기]버튼, [펜모드]버튼, [지우개모드]버튼, [색상선택]버튼, [굵기]텍스트필드를 비활성화.
					// 받은 닉네임(=출제자)이 나 자신이라면, 채팅을 보낼 수 없도록 chat_textfield(?), send_button(?)을 비활성화
					// 한다.
					break;
				case Protocol._DAP:
					// 받은 닉네임(=출제자)이 나 자신이라면, [KosmoCatch] 라벨의 text를 받은 '답'으로 변경해주어, 출제자 클라이언트가 답에
					// 해당하는 그림을 그릴 수 있도록 한다.
					break;
				case Protocol._CHAT:
					// 클라이언트 측의 JTextArea에 "[닉네임] 메세지/채팅 내용"를 출력(append)해준다.
					break;
				case Protocol._CORRECT:
					// 클라이언트 측의 JTextArea에 "★★[닉네임]님 정답★★"를 출력(append)해준다.
					// 받아온 닉네임에 해당하는 클라이언트의 점수라벨을 갱신한다.(+10점)
					// [현재랭킹패널]: 해당 클라이언트의 점수를 갱신한다. - 닉네임을 이용하면 row를 int로 반환 받을 수 잇음.
					break;
				case Protocol._OVER_MEM:
					// 대기실에서 인원이 모두 찬 방을 더블클릭하였을 때 처리해주어야 할 내용임.
					// 인원이 4명 초과시 새로 접속하려는 사람에게 JOptionPane.ShowMessageDialog 으로 "인원을 초과하였습니다"를 띄우고
					// 로그인 창으로 다시 되돌아가도록 한다.
					break;
				case Protocol._PAINT:
					// 출제자도 서버 스레드를 거친 뒤 화면에 그림이 그려지도록 하자.
					// Graphics와 start 및 end 지점에 해당하는 4개의 좌표를 이용해서 그림을 화면에 그려준다.
					break;
				case Protocol._ERASE:
					//
					break;
				case Protocol._EXIT:
					// 받아온 닉네임에 해당하는 클라이언트를 왼쪽 랭킹 테이블에서 제거(remove)해준다.
					// 해당 클라이언트의 라벨의 텍스트를 빈문자열("")로 변경해준다.
					// 클라이언트 측의 JTextArea에 "[닉네임]님이 퇴장하였습니다."를 출력(append)해준다.
					break;
//					case Proto:
				//
//						break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) {
//
//	}

}
