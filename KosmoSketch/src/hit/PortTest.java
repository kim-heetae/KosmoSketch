package hit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

class A extends Thread {
	Port						port		= Port.getPort();
	int							portNum		= 0;
	static ArrayList<Integer>	portNumList	= new ArrayList<>();
	StringBuffer	sb = new StringBuffer();

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			portNum = port.getPortNum();
			portNumList.add(portNum);
			System.out.println("가져온 포트번호: " + portNum);
			sb.append(port.portList.toString()+"\n\n");
		}
//////////////////////////////////////////////////
		String fileName = "C:\\app\\test11.txt";

		try {

// BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

// 파일안에 문자열 쓰기
			fw.write(sb.toString());
			fw.flush();

// 객체 닫기
			fw.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
//////////////////////////////////////////////////

	}
}

class B extends Thread {
	Random	r		= new Random();
	Port	port	= Port.getPort();

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			if (A.portNumList.size() > 0) {
				port.returnPort(A.portNumList.get(r.nextInt(A.portNumList.size())));
			}
		}
	}
}

public class PortTest {

	public static void main(String[] args) {
		A a = new A();
		a.start();
//		try {
//			Thread.sleep(3000);
//		}
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		B b = new B();
		b.start();
	}

}
