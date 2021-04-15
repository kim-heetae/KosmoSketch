package yj;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TestServer implements Runnable{
	Room room = null;
	ServerSocket server = null;
	boolean isStop;
	Socket socket = null;
	List<Map<String, Object>> Room;
	WaitRoomServerThread wrst;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	@Override
	public void run() {
		isStop = false;
		Room = new Vector<>();
		wrst = new WaitRoomServerThread(this);
		try {
			server = new ServerSocket(3002);
			ois = new ObjectInputStream(socket.getInputStream());
			while(!isStop) {
				socket = server.accept();
				wrst.start();//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
