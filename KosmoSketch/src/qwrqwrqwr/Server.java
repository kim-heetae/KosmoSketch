package qwrqwrqwr;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Server extends JFrame{//view
	
	JTextArea jta;
	
	public Server(){
		initDisplay();
	}
	
	public void initDisplay() {
		jta = new JTextArea();
		new ServerLogic(this);
		this.add(jta);
		this.setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
//	public void init() {
//		ServerSocket server = null;
//		boolean isFlag = false;
//		
//		try {
//			server = new ServerSocket(16332);
//			while (!isFlag) {
//				Socket client = server.accept();
//				jta.append(client.toString()+"입장");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	

	public static void main(String[] args) {
		new Server();
	}

}
