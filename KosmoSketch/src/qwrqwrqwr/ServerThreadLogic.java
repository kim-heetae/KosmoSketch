package qwrqwrqwr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;


public class ServerThreadLogic extends Thread{
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ServerLogic sl = null;
	
	public ServerThreadLogic(ServerLogic sl) {
		this.sl = sl;
		this.start();
	}
	public void broadCasting(String msg) {
		for (ServerThreadLogic stl : sl.globalList) {
			try {
				stl.oos.writeObject(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void asdasdas() {
		
	}
	
	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(sl.socket.getOutputStream());
			ois = new ObjectInputStream(sl.socket.getInputStream());
//			broadCasting(Protocol._CLIENT_INFO + );
			boolean isStop = false;
			while(!isStop){
				try {
					String msg = ois.readObject().toString().trim();
					StringTokenizer st = new StringTokenizer(msg, "#");
					int protocol = Integer.parseInt(st.nextToken());
					switch (protocol) {
					case Protocol._READY:
						/*
						 * 
						 */
//						sl.readyCount++;
						break;
						
					case Protocol._NOT_READY:	
//						sl.readyCount--;
						break;
					case Protocol._CHAT:
//						broadCasting(200 + "#" + msg + "#" + msg);
						break;
					
					case Protocol._PAINT:
						
						break;
					case Protocol._EXIT:
						
						break;
						
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
