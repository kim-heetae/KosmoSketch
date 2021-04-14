package qwrqwrqwr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerLogic implements Runnable{
	Socket socket = null;
	ServerSocket server = null;
	ServerView sv = null;
	Thread welcometh = null;
	List<ServerThreadLogic> globalList = null;
	ServerThreadLogic stl = null;
	
	String [] daps = new String[100];
	String dap = null;
	int turnNum = 0;
	int readyCount = 0;
	
	
	public ServerLogic(ServerView sv){
		this.sv = sv;
		welcometh = new Thread(this);
		welcometh.start();
	}
		@Override
		public void run() {
			boolean isFlag = false;
			globalList = new Vector<>();
			try {
				server = new ServerSocket(7375);
				while (!isFlag) {
					socket = server.accept();
					stl = new ServerThreadLogic(this);
					///////////////////////////////////////////////
					if(globalList.size() > 3) {
						stl.send(Protocol._OVER_MEM+"");
					}
					//////////////////////////////////////////////
					globalList.add(stl);
					sv.jta.append(socket.toString()+"입장");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}

}
