package paint.test.sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class PotatoServerThread extends Thread {
	
	public PotatoServer ps = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public PotatoServerThread(PotatoServer ps) {
		// 전역변수임을 구분할 수 있도록 "상징적으로" this를 붙임.
		this.ps = ps;
		this.client = ps.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			ps.globalList.add(this);
			if(ps.globalList.size() > 4) {
				oos.writeObject("600");	//접속자 수 초과 알림
				ps.jta_server.append("600#접속자 수 초과\n");
				ps.globalList.remove(ps.globalList.size()-1);
				try {
//					this.finalize();
				}
				catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(this.isAlive());
			}
			System.out.println(ps.globalList);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}/////////////////////// [[end of TomatoServerThread]] /////////////

	public void broadCasting(String broad) {
		for (PotatoServerThread pst : ps.globalList) {
			pst.send(broad);
		}
	}

	public void send(String broad) {
		try {
			oos.writeObject(broad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		boolean isStop = false; 
		
		
		
		try {
			while(!isStop) {
				String msg = ois.readObject().toString();
				ps.jta_server.append(msg+"\n");
				System.out.println(ps.globalList);
				for(PotatoServerThread pst : ps.globalList) {
					pst.oos.writeObject(msg);
				}
			}
		} catch (Exception e) {
		}
//		try {
//			while(!isStop) {
//				Thread.sleep(4000);
//				System.out.println(123);
//				ps.currentTime = String.format("00:00:%02d", ps.timerSet);
//				ps.jta_server.setText(ps.currentTime);
//				for(PotatoServerThread pst : ps.globalList) {
//					pst.oos.writeObject(ps.currentTime);
//				}
//				if(this == ps.globalList.get(0)) {
//					ps.timerSet--;
//				}
//				ps.getTimer(this);
//				Thread.sleep(1000);
//				ps.jta_server.setText(ps.currentTime);
//				if(ps.timerSet == 0) {
//					//서버에 알리고
//					return;
//				}
//			}
//		} catch (Exception e) {
//		}
//		boolean isStop = false;
//			while (!isStop) {
//				try {
//					broad = String.valueOf((char)Integer.parseInt(ois.readObject().toString()));
//					for(PotatoServerThread pst : ps.globalList) {
//						if(pst != this) {
//							pst.oos.writeObject(broad);
//						}
//						pst.oos.writeObject(ps.getTimer());
					}
//					ps.jta_server.append(ps.getTimer());
//				} catch (Exception e) {
//				}
//					String msg = String.format("%c", keycode);
//					System.out.println(keycode);
//					ps.jta_server.append(keycode + "\n");
//		} ///////////////// end of while
//	}///////////////////////// end of run
	
	
}
