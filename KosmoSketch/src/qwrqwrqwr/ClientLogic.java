package qwrqwrqwr;

import java.awt.Container;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import kosmo.sketch.proj.Protocol;

public class ClientLogic extends Thread {
	Container			cont		= null;
	String				questioner	= null;
	Socket				socket		= null;
	ObjectOutputStream	oos			= null;
	ObjectInputStream	ois			= null;

	public void init() {

		try {
			socket	= new Socket("localhost", 16332);
			oos		= new ObjectOutputStream(socket.getOutputStream());
			ois		= new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void run() {
		boolean isStop = false;
		while (!isStop) {
			try {
				String			msg			= (String) ois.readObject();
				StringTokenizer	tokenizer	= new StringTokenizer(msg, "#");
				int				protocol	= Integer.parseInt(tokenizer.nextToken());	// 프로토콜을 가져와서 담음.
				String			nickname	= tokenizer.nextToken();					// 닉네임을 가져와서 담음.
				switch (protocol) {
					case Protocol._CLIENT_INFO:
							// 라벨의 초기화
							// 클라이언트 측의 JTextArea에 "[닉네임]님이 입장하셨습니다."를 출력(append)해준다.
						break;
					case Protocol._READY:
							// 받아온 닉네임에 해당하는 클라이언트의  READY라벨 색을 변경한다. (white > orange)
							// 라벨의 텍스트도 NOT READY > READY 로 변경한다.
						break;
					case Protocol._NOT_READY:
							// 받아온 닉네임에 해당하는 클라이언트의  READY라벨 색을 변경한다. (orange > white)
							// 라벨의 텍스트도 READY > NOT READY 로 변경한다.
						break;
					case Protocol._START:	//서버쪽에서 start를 보내면서 (출제자)닉네임을 같이 보내줘야 '비활성화'를 처리할 수 있다.
							// ****(임시명)paint()를 호출하기 전에 현재 클라이언트가 '출제자'인지를 항상 if문으로 체크한다.
							// [게임준비]버튼, [나가기]버튼, [모두 지우기]버튼, [펜모드]버튼, [지우개모드]버튼, [색상선택]버튼, [굵기]텍스트필드를 비활성화.
							// 받은 닉네임(=출제자)이 나 자신이라면, 채팅을 보낼 수 없도록 chat_textfield(?), send_button(?)을 비활성화 한다.
						break;
					case Protocol._DAP:
							// 받은 닉네임(=출제자)이 나 자신이라면, [KosmoCatch] 라벨의 text를 받은 '답'으로 변경해주어, 출제자 클라이언트가 답에 해당하는 그림을 그릴 수 있도록 한다.
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
							// 인원이 4명 초과시 새 접속하려는 사람에게 JOptionPane.ShowMessageDialog 으로 "인원을 초과하였습니다"를 띄우고 로그인 창으로 다시 되돌아가도록 한다.   
						break;
					case Protocol._PAINT:
							// 
						break;
					case Protocol._EXIT:
	
						break;
				}
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

	}

}
