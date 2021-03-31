package trash.can;

import java.awt.*;

public class EraseTest extends Frame {
	public EraseTest(String title) { // 생성자
		super(title);
		super.paint(this.getGraphics());
	}

	public void paint(Graphics g) {

		g.drawLine(10, 30, 50, 50); // 선을 그린다.

		g.drawRect(60, 40, 50, 50); // 사각형을 그린다.

		g.drawString("Hello!", 120, 50); // 문자열을 그린다.

	}

	public static void main(String[] args) {

		EraseTest f = new EraseTest("paint");

		f.setSize(200, 100);

		f.setVisible(true); // 프레임이 보여질 때 paint가 호출된다.

	}

}