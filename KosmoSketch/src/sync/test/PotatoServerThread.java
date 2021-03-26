package sync.test;

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
	String broad = null;

	public PotatoServerThread(PotatoServer ps) {
		// 전역변수임을 구분할 수 있도록 "상징적으로" this를 붙임.
		this.ps = ps;
		this.client = ps.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			ps.globalList.add(this);
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
			while (!isStop) {
				try {
					broad = String.valueOf((char)Integer.parseInt(ois.readObject().toString()));
					for(PotatoServerThread pst : ps.globalList) {
						if(pst != this) {
							pst.oos.writeObject(broad);
						}
					}
					ps.jta_server.append(broad);
				} catch (Exception e) {
				}
//					String msg = String.format("%c", keycode);
//					System.out.println(keycode);
//					ps.jta_server.append(keycode + "\n");
		} ///////////////// end of while
	}///////////////////////// end of run
	
	
}
