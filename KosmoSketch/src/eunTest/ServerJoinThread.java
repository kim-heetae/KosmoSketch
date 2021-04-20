//package eunTest;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.StringTokenizer;
//
//import javax.swing.JOptionPane;
//
//import test.project1.Protocol;
//
//public class ServerJoinThread extends Thread {
//	SoS					sos	= null;
//
//	ObjectOutputStream	oos	= null;
//	ObjectInputStream	ois	= null;
//
//	public ServerJoinThread(SoS sos, ObjectOutputStream oos, ObjectInputStream ois) {
//		this.sos = sos;
//		this.oos = oos;
//		this.ois = ois;
//		this.start();
//	}
//
//	@Override
//	public void run() {
//		boolean	isStop		= false;
//		String	value		= null;
//		int		protocol	= 0;
//		runStart: while (!isStop) {
//			try {
//				value = ois.readObject().toString();
//				StringTokenizer st = new StringTokenizer(value, Protocol._CUT);
//				protocol = Integer.parseInt(st.nextToken());
//				switch (protocol) {
//				// 아이디인 경우
//				case Protocol._CHECK_ID:
//					String input_ID = st.nextToken();
//					oos.writeObject(Protocol._CHECK_ID + Protocol._CUT + " " + Protocol._CUT + sos.cdao.isDuplicatedID(input_ID));
//					break;
//				// 닉네임인 경우
//				case Protocol._CHECK_NICKNAME:
//
//					break;
//				// 이메일 전송인 경우
//				case Protocol._SEND_EMAIL:
//
//					break;
//				}
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
