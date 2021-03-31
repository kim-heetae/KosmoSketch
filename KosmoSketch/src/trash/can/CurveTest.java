package trash.can;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CurveTest {

	Graphics	g;					// 프레임의 Graphics 객체를
									// 위한 변수

	int			x, y, ox, oy;		// 움직인 후의 좌표(x, y)와
									// 움직이기 전의 좌표(ox,
									// oy)

	JFrame		jf	= new JFrame();

//	public void paintComponent(Graphics g) {
//		// Approach 1: Dispaly image at at full size
////             g.drawImage(new ImageIcon("src\\trash\\can\\bgImage.jpg").getImage(), 0, 0, null);
//		// Approach 2: Scale image to size of component
//		// Dimension d = getSize();
//		// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
//		// Approach 3: Fix the image position in the scroll pane
//		// Point p = scrollPane.getViewport().getViewPosition();
//		// g.drawImage(icon.getImage(), p.x, p.y, null);
////             setOpaque(true); //그림을 표시하게 설정,투명하게 조절
////             super.paintComponent(g);
//	}

	JPanel background = new JPanel() {
        public void paintComponent(Graphics g) {
            // Approach 1: Dispaly image at at full size
//            g.drawImage(new ImageIcon("src\\trash\\can\\bgImage.jpg").getImage(), 0, 0, null);
            // Approach 2: Scale image to size of component
            // Dimension d = getSize();
            // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
            // Approach 3: Fix the image position in the scroll pane
            // Point p = scrollPane.getViewport().getViewPosition();
            // g.drawImage(icon.getImage(), p.x, p.y, null);
            setOpaque(false); //그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
        }
    };
	
	
	public CurveTest(String title) { // 생성자
//		jf.paintComponents(g);
//		jf.setIconImage(new ImageIcon("src\\trash\\can\\bgImage.jpg").getImage());
		jf.setContentPane(background);
		jf.setTitle(title);
		jf.setLayout(new BorderLayout());
		jf.setSize(1000, 1000);
		jf.setVisible(true);
		System.out.println(jf.getBackground().toString());
		g = jf.getGraphics(); // 프레임의 Graphics 객체를 얻는다.
//		g.drawImage(new ImageIcon("src\\trash\\can\\bgImage.jpg").getImage(),0,0,null);
		g.setColor(Color.red); // 그리기 색을 빨간 색으로 정한다.

		// 마우스 움직임 이벤트 처리

		jf.addMouseMotionListener(new MouseMotionAdapter() { // MouseMotionAdapter는 MouseMotionListener를 implements한
																// 추상클래스임.

			// MouseMotionAdapter의 메소드임.
			public void mouseDragged(MouseEvent e) { // 마우스가 움직이면

				x	= e.getX();
				y	= e.getY();	// 마우스의 현재 위치를 알아온다.

				// 전 위치부터 현재 위치까지 직선을 긋는다.

				g.drawLine(ox, oy, x, y);
//				g.draw3DRect(ox, oy, 100, 100, true);
//				g.fill3DRect(ox, oy, 100, 100, true);

				ox	= x;
				oy	= y;	// x와 y를 ox와 oy에 대입한다.

			}

		});

		// 마우스 이벤트 처리

		jf.addMouseListener(new MouseAdapter() {

			// 마우스를 누르면 호출된다.

			public void mousePressed(MouseEvent e) {

				ox	= e.getX();
				oy	= e.getY();	// 마우스의 위치를 기억한다.

//				g.copyArea(ox-30, oy-30, 60, 60, 10, 10);
//				g.clipRect(ox, oy, 100, 100);
//				g.drawImage(new ImageIcon("src\\trash\\can\\bgImage.jpg").getImage(), 0, 0, 1000, 1000, null, new ImageObserver() {
					
//					@Override
//					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//				});
				g.clearRect(ox - 30, oy - 30, 60, 60);

			}

		});

	}

	public static void main(String[] args) {

		CurveTest f = new CurveTest("도형 그리기");

	}

}