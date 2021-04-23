package trash.can;

import javax.swing.JFrame;

public class A {
//	catchFrame a = new catchFrame();
	public A() {
		Thread th = new Thread();
		th.run();
		Thread th1 = new Thread();
		th1.run();
	}
	
//	public void method() {
//		System.out.println("메소드 호출 성공!");
//	}
//	
	public static void main(String[] args) {
		new A();
	}
}
