package test.project1;

import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

//class ClientLogic의 ois를 통해 읽어들인 좌표 정보를 이용해
//(보낸 닉네임이 나 자신이 아니라면) 패널에 그림을 똑같이 구현해주는 역할을 담당하는 클래스이다.
public class PaintReceiverLogic implements Runnable, MouseListener, MouseMotionListener{

	// [test] start
	Socket				client			= null;
	ObjectOutputStream	oos				= null;
	ObjectInputStream	ois				= null;
	String				client_nickname	= null;
	TestPanelForPaint	tpfp			= null;
	int					startX			= 0;
	int					startY			= 0;
	int					endX			= 0;
	int					endY			= 0;
	// [test] end

//	public PaintReceiverLogic() {
//
//	}
	
	public PaintReceiverLogic(TestPanelForPaint tpfp) {
		this.tpfp = tpfp;
		client_nickname = JOptionPane.showInputDialog("[Paint 단위 테스트] 닉네임을 입력하세요");
		tpfp.setTitle(client_nickname);
		try {
			client = new Socket("localhost", 1996);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			oos.writeObject(Protocol._CLIENT_INFO + "#" + client_nickname);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = (String)ois.readObject();
				StringTokenizer st = new StringTokenizer(msg, "#");
				int protocol = Integer.parseInt(st.nextToken());
				String nickname = st.nextToken();
				switch (protocol) {
					case Protocol._PAINT:
//						if(!client_nickname.equals(nickname)) {
							int startX = Integer.parseInt(st.nextToken());
							int startY = Integer.parseInt(st.nextToken());
							int endX = Integer.parseInt(st.nextToken());
							int endY = Integer.parseInt(st.nextToken());
							tpfp.graphics2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,0));
							tpfp.graphics2d.drawLine(startX+10, startY+33, endX+10, endY+33);
//					}
					
					break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//////////////////////[[인터페이스 메소드 오버라이딩 -- 이벤트 처리 클래스의 역할을 하는 부분]]/////////////////////////////
	@Override
	public void mouseDragged(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		try {
			oos.writeObject(Protocol._PAINT + "#" + client_nickname 
											+ "#" + startX + "#" + startY 
											+ "#" + endX + "#" + endY);
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tpfp.graphics2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,0));
		tpfp.graphics2d.drawLine(startX+10, startY+33, endX+10, endY+33);
        startX = endX;
        startY = endY;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	

	// [test] start
//	public static void main(String[] args) {
//		new PaintReceiverLogic();
//	}
	// [test] end
}
