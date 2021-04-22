package paint.test.sync;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

public class PaintThreadTest extends JFrame {

	//그리는 도구(Graphics 대용): TextPane / TextPane 위에 TextComponent ...
	
	JPanel jp;
	JButton jbtn;
	Graphics graphics;
	Graphics2D g;
	int startX;
	int startY;
	int endX;
	int endY;
	PotatoClientThread pct;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public PaintThreadTest() {
		initDisplay();
		graphics = getGraphics();
		g = (Graphics2D)graphics;
		g.setColor(Color.blue);
		try {
			Socket client = new Socket("localhost", 3002);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pct = new PotatoClientThread(this);
		pct.start();
	}
	
	public void initDisplay() {
		
		jp = new JPanel();
		jp.setOpaque(true);
		jp.setBackground(Color.black);
		jbtn = new JButton("전체 지우기");
		
		jp.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
                startX = e.getX(); 
                startY = e.getY(); 
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		jp.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				endX = e.getX(); 
                endY = e.getY();
                try {
					oos.writeObject(startX+"#"+startY+"#"+endX+"#"+endY);
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,0)); //선굵기
                g.drawLine(startX+10, startY+33, endX+10, endY+33);
                startX = endX;
                startY = endY;
			}
		});
		
		jbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼 누름");
				System.out.println(this.getClass());
//				remove(jp);
				repaint();
//				revalidate();
			}
		});
		this.add("North", jbtn);
		this.add("Center", jp);
		this.setSize(1000, 800);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new PaintThreadTest();
	}


}
