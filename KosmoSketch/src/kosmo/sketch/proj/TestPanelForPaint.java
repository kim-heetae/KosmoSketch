package kosmo.sketch.proj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanelForPaint extends JFrame {

	Graphics			graphics		= null;
	Graphics2D			graphics2d		= null;
	JPanel				jp				= null;
	PaintReceiverLogic	prl				= null;
	
	public TestPanelForPaint() {
		prl = new PaintReceiverLogic(this);
		initDisplay();
		graphics = getGraphics();
		graphics2d = (Graphics2D)graphics;
		graphics2d.setColor(Color.blue);
		
	}
	
	public void initDisplay() {
		jp = new JPanel();
		jp.setBackground(Color.white);
		this.add("Center", jp);
		jp.addMouseListener(prl);
		jp.addMouseMotionListener(prl);
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestPanelForPaint();
	}



}
