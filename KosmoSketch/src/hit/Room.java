package hit;

import java.io.IOException;

import eunTest.SoS;

public class Room {
	InOutServer inoutServer = null;
	ChatServer chatServer  = null;
	TimerServer timerServer = null;
	PaintServer paintServer = null;
	SoS sos = null;
	
	public Room() {
		try {
			inoutServer = new InOutServer();
			chatServer = new ChatServer();
			timerServer = new TimerServer();
			paintServer = new PaintServer();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
