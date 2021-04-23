package eunTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class ForeServerThread extends Thread {

	SoS					sos			= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;
	int					code		= 0;

	// 회원가입 시 받아오는 클라이언트의 정보
	String				id			= null;
	String				pw			= null;
	String				nickname	= null;
	String				email		= null;
	Room				room		= null;

	public ForeServerThread(SoS sos, ObjectOutputStream oos, ObjectInputStream ois) {
		this.sos = sos;
		this.oos = oos;
		this.ois = ois;
	}

	@Override
	public void run() {
		boolean	isStop		= false;
		String	msg			= null;
		int		protocol	= 0;
		runStart: while (!isStop) {
			try {
				msg = ois.readObject().toString();
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				// _LOGIN + # + id + # + pw 를 받는다.
				case Protocol._LOGIN:
					String pid = st.nextToken();
					String ppw = st.nextToken();
					// 클라이언트로부터 받아온 id와 pw를 DAO로 보내 존재여부를 확인한다.
					LoginDAOImpl loginDAO = new LoginDAOImpl();
					System.out.println("여기는 실행되니?");
					String result = loginDAO.checkPw(pid, ppw);
					// "존재하지 않는 아이디입니다." || "비밀번호를 확인해주세요."
					if ("존재하지 않는 아이디입니다.".equals(result) || "비밀번호를 확인해주세요.".equals(result)) {
						oos.writeObject(Protocol._LOGIN_FAILURE + Protocol._CUT + result);
					}
					// "로그인 성공"
					else if ("로그인 성공".equals(result)) {
						String nickName = loginDAO.ldto.getnickname();
						this.nickname = nickName;
						// 클라이언트측에서 _CLIENT_INFO 프로토콜을 받았다는 것 자체가 로그인에 성공하였음을 의미함. (접속 성공 시 딱 한번만 받는
						// 프로토콜이다.)
////////////////////////////단위테스트임
//						Room room = new Room(sos.roomList.size()+1, "이거이거", "요기요기");
//						sos.roomList.add(room);

						// 닉네임이 이미 접속해 있으면
						if (sos.clientList.containsKey(nickName)) {
							oos.writeObject(Protocol._LOGIN_FAILURE + Protocol._CUT + "이미 로그인된 아이디입니다.");
						} else {
							sos.clientList.put(nickName, this);
							oos.writeObject(Protocol._CLIENT_INFO + Protocol._CUT + nickName + Protocol._CUT + result);
							if (sos.roomList.size() != 0) {
								sos.sendRoomInfo(oos);
							}
						}
					}
					break;
				case Protocol._CHECK_ID:
					String inputID = st.nextToken();
					CheckDAO cdao = new CheckDAO();
					String resultMSG = cdao.isDuplicatedID(inputID);
					oos.writeObject(Protocol._CHECK_ID + Protocol._CUT + resultMSG);
					if ("사용가능한 아이디입니다".equals(resultMSG)) {
						id = inputID;
					} else {
						id = null;
					}
					break;
				case Protocol._CHECK_NICKNAME:
					String pnickname = st.nextToken();
					cdao = new CheckDAO();
					resultMSG = cdao.isDuplicatedNickname(pnickname);
					oos.writeObject(Protocol._CHECK_NICKNAME + Protocol._CUT + resultMSG);
					if ("사용 가능한 닉네임입니다.".equals(resultMSG)) {
						nickname = pnickname;
					} else {
						nickname = null;
					}
					break;
				case Protocol._SEND_EMAIL:
					String pemail = st.nextToken();
					MailCheck mc = new MailCheck();
					code = mc.setReceiveEmail(pemail);
					if (code == 404) {
						oos.writeObject(String.valueOf(Protocol._EMAIL_FAILURE));
						email = null;
					} else {
						oos.writeObject(String.valueOf(Protocol._EMAIL_SUCCESS));
						email = pemail;
					}
					break;
				case Protocol._CHECK_CODE:
					int inputCode = Integer.parseInt(st.nextToken());
					if (code == inputCode) {
						oos.writeObject(String.valueOf(Protocol._CODE_SUCCESS));
					} else {
						oos.writeObject(String.valueOf(Protocol._CODE_FAILURE));
					}
					break;
				case Protocol._JOIN:
					pid = st.nextToken();
					// 입력된 비밀번호를 받아옴
					ppw = st.nextToken();
					pnickname = st.nextToken();
					pemail = st.nextToken();
					// 전역변수에 담음 // pw를 제외한 다른 값들은 중복확인시에 초기화되었음.
					pw = ppw;
					System.out.println(id + ", " + pw + ", " + nickname + ", " + email);
					System.out.println("받아온 값====> " + pid + ", " + ppw + ", " + pnickname + ", " + pemail);
					if (id != null && pw != null && nickname != null && email != null && id.equals(pid)
							&& nickname.equals(pnickname) && email.equals(pemail)) {
						Map<String, String> newbie = new HashMap<>();
						newbie.put("ID", id);
						newbie.put("PW", pw);
						newbie.put("NICKNAME", nickname);
						newbie.put("EMAIL", email);
						loginDAO = new LoginDAOImpl();
						loginDAO.insertMember(id, pw, nickname, email);
						System.out.println(newbie);
						oos.writeObject(String.valueOf(Protocol._JOIN_SUCCESS));
					} else {
						oos.writeObject(String.valueOf(Protocol._JOIN_FAILURE));
					}
					break;
				case Protocol._MAKEROOM:
					String roomName = st.nextToken();
					oos.writeObject(Protocol._MAKEROOM + Protocol._CUT + roomName);
					room = null;
					room = new Room(sos.roomList.size() + 1, nickname, roomName);
					sos.roomList.add(room);
					String imsiMsg = Protocol._ROOM_INFO + Protocol._CUT + room.roomNum + Protocol._CUT + roomName
							+ Protocol._CUT + room.nickNameList.size() + Protocol._CUT + room.isGamePlay;
					sos.broadCasting(imsiMsg);
					break;
				case Protocol._ROOMIN:
					int roomNum = Integer.parseInt(st.nextToken());
					for (Room r : sos.roomList) {// 클라이언트가 접속을 시도한 방찾기
						if (roomNum == r.roomNum) {// 방번호가 같은 방일때
							this.room = r;
							if (r.nickNameList.size() < 4) {// 해당 방의 인원이 4명보다 작을때
								r.nickNameList.add(nickname);
								oos.writeObject(Protocol._ROOM_WELCOME + Protocol._CUT + r.roomName);
								imsiMsg = (Protocol._ROOM_UPDATE + Protocol._CUT + r.roomNum + Protocol._CUT
										+ r.roomName + Protocol._CUT + r.nickNameList.size() + Protocol._CUT
										+ r.isGamePlay);
								sos.broadCasting(imsiMsg);
//								for(ForeServerThread fst : sos.clientList.values()) {
//									sos.sendRoomInfo(fst.oos);
//								}
							} else {// 해당 방이 꽉찼을때
								oos.writeObject(String.valueOf(Protocol._ROOM_REJECTED));
							}
						}
					}
					break;
				// 클라이언트가 게임방에서 나가기 버튼을 눌렀을 떄 받게 되는 메세지
				// _ROOMOUT + # + 닉네임
				case Protocol._ROOMOUT:
//					int roomnum = Integer.parseInt(st.nextToken());
//					for(Room r : sos.roomList) {
//						if(r.roomNum == roomnum) {
							if (room.nickNameList.size() > 1) {
								// 방의 클라이언트 닉네임 리스트에서 해당 클라이언트를 제거
								room.nickNameList.remove(nickname);
								// 해당 클라이언트가 방을 나갔음을 broadcasting - 참여중인 인원수를 수정할 수 있도록 해준다.
								// 방번호 + 참여중인 인원수
								sos.broadCasting(Protocol._ROOMOUT + Protocol._CUT + room.roomNum + Protocol._CUT
										+ room.nickNameList.size());
							}
							// 혼자 남은 클라이언트가 나가기 버튼을 눌렀을 경우 - 방 폭발(CLOSE ROOM)
							else {
								sos.broadCasting(Protocol._CLOSEROOM + Protocol._CUT + room.roomNum);
								// 해당 방을 방리스트에서 삭제 - 이렇게 하면 자원관리도 끝???
								sos.roomList.remove(room);
							}							
//						}
//					}
					break;
				case Protocol._LOGOUT:
					sos.clientList.remove(nickname);
					oos.writeObject(Protocol._LOGOUT + "");
					break;
				case Protocol._EXIT:
					sos.clientList.remove(nickname);
					// 소켓관련Exception(oos, ois 직렬화 문제로 발생; SocketException, EOFException)을 해결하려면
					// 서버스레드가 종료되어야 함.
//					isStop = true;
//					break;
					break runStart;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
