package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class TimerServer extends ServerSocket{
	Socket client = null;
	public TimerServer() throws IOException {
		super(Port._TIMER);
		client = this.accept();
	}

}
