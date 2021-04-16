package qwrqwrqwr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	Socket	client_paint	= null;
	Socket	client_chat		= null;
	Socket	client_timer	= null;
	Socket	client_inout	= null;
	
	//as 필요합읍니다/////////////////////////
	Socket client_wait		= null;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	boolean isLoggedIn = false;
	String id = null;
	String pw= null;
	TestClientView tc = null;
	///////////////////////////////////////
	
	// 생성자
	
	public ClientTest() {
		
	}
	
	public ClientTest(TestClientView tc) {
		this.tc = tc;
		getSockets();
	}

	// 소켓생성 메소드
	public void getSockets() {
		try {
			client_wait = new Socket("localhost", 10329);
			oos		= new ObjectOutputStream(client_wait.getOutputStream());
			ois		= new ObjectInputStream(client_wait.getInputStream());
			id = tc.idField.getText();
			pw = tc.pwField.getText();
//			System.out.println(id+"\n"+pw);
			oos.writeObject(id+"#"+pw);
			String msg = ois.readObject().toString();
			while(!isLoggedIn) {
			if(msg == "_LOGINSUCCESSS") {								//프로토콜만들어주새요 1
				client_paint = new Socket("localhost", 7375);
				client_chat	 = new Socket("localhost", 7375);
				client_timer = new Socket("localhost", 7375);
				client_inout = new Socket("localhost", 7375);
				isLoggedIn = true;
			} else if(msg == "_IDFAILURE") {							//프로토콜만들어주새요 2
				//클라 (로그인뷰)에서 OK버튼 하나달린 메세지창?으로 id실패 알리고33번줄로 어떻게 돌아가지
			} else if(msg == "_PWFAILURE") {							//프로토콜만들어주새요 3
				//클라 (로그인뷰)에서 OK버튼 하나달린 메세지창?으로 pw실패 알리고33번줄로 어떻게 돌아가지
			}
			}
		}catch (Exception e) {
		}
	}
	

}