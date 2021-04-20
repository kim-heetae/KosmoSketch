package yj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import test.project1.Protocol;

public class ServerLoginThread extends Thread {

	SoS						sos						= null;
	WaitRoomServerThread	waitRoomServerThread	= null;

	ObjectOutputStream		oos						= null;
	ObjectInputStream		ois						= null;
	String					nickName				= null;

	public ServerLoginThread(SoS sos, ObjectOutputStream oos, ObjectInputStream ois) {
		this.sos = sos;
		this.oos = oos;
		this.ois = ois;
		System.out.println(1);
		this.start();
	}

	@Override
	public void run() {
		System.out.println(2);
		boolean isStop = false;
		runStart: while (!isStop) {
			String	msg				= null;
			String	id				= null;
			String	pw				= null;
			String	msg_isLoggedIn	= null;
			try {
				// 클라이언트가 로그인 버튼을 누를 때 클라이언트쪽에서 Socket이 생성되고 곧바로 입력한 id와 pw 정보를 stream을 통해 서버로
				// 보낸다.
				// msg: _LOGIN#아이디#패스워드
				System.out.println(sos.waitRoomClientSocket);
				msg = ois.readObject().toString();
				System.out.println("보이니1");
				StringTokenizer st = new StringTokenizer(msg, Protocol._CUT);
				id = st.nextToken();
				pw = st.nextToken();
				System.out.println(id);
				System.out.println(pw);
				msg_isLoggedIn = sos.loginDAO.checkPw(id, pw);
				System.out.println(msg_isLoggedIn);
				System.out.println("보이니2");
//				System.out.println(id + ", " + pw);
				// 데이터 연동을 위한 DAO 클래스 인스턴스 생성
				// insert here//////////////////////////////
				// DAO 클래스를 통해 로그인 성공 여부(id와 pw 존재, 일치 여부)가 파악되었으면, 그 정보를 클라이언트에게도 알려야 한다.
				/*
				 * DAO 완성되면 주석 제거해야 됨~~~~! // DB에서 id를 이용해서 nickName 가져와 변수에 담기~~!
				 */
				////////////////////////////////////////////////////////////////////////////////////////////////////
				// DAO - DB - 프로시저 를 거쳐 가져온 msg(out)에 따라 경우를 나누어 처리한다.
				// "존재하지 않는 아이디입니다." || "비밀번호를 확인해주세요."
				if ("존재하지 않는 아이디입니다.".equals(msg_isLoggedIn) || "비밀번호를 확인해주세요.".equals(msg_isLoggedIn)) {
					oos.writeObject(Protocol._LOGIN_FAILURE + Protocol._CUT + " " + Protocol._CUT + msg_isLoggedIn);
				}
				// "로그인 성공"
				else if ("로그인 성공".equals(msg_isLoggedIn)) {
					nickName = sos.loginDAO.ldto.getnickname();
					// 클라이언트측에서 _CLIENT_INFO 프로토콜을 받았다는 것 자체가 로그인에 성공하였음을 의미함. (접속 성공 시 딱 한번만 받는
					// 프로토콜이다.)
					oos.writeObject(Protocol._CLIENT_INFO + Protocol._CUT + nickName + Protocol._CUT + msg_isLoggedIn);
					sos.clientList.put(nickName, waitRoomServerThread);
					waitRoomServerThread = new WaitRoomServerThread(sos, oos, ois);
					Thread th = new Thread(waitRoomServerThread);
					th.start();
					break runStart;
				} else {
					System.out.println("[코드확인필요] 로그인 정보를 불러오는 데 실패하였습니다.");
					break runStart;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
