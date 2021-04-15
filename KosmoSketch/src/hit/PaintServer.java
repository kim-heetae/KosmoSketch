package hit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eunTest.Port;

public class PaintServer extends ServerSocket{
	Socket client = null;
	public PaintServer() throws IOException {
		super(Port._PAINT);
		client = this.accept();
	}

}
