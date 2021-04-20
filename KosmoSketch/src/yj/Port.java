package yj;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Port {
	List<Integer>		portList	= new Vector<>();

	private static Port	port		= null;

	private Port() {
	}

	public static Port getPort() {
		if (port == null) {
			port = new Port();
		}
		return port;
	}

	public static int	_WAITROOM	= 9876;
//	public static int	_INOUT		= 20000;
//	public static int	_CHAT		= 30000;
//	public static int	_TIMER		= 40000;
//	public static int	_PAINT		= 50000;
//	public static int	_PORT		= 10000;

	public synchronized int getPortNum() {
		Random	portNumGenerator	= new Random();
		int		portNum				= 0;
		do {
			portNum = portNumGenerator.nextInt(60001);
		}
		while (portNum < 10000 || portList.contains(portNum));
		portList.add(portNum);
		return portNum;
	}
//	public synchronized int getPortNum() {
//		int portNum = 0;
//		if (portList.size() == 0) {
//			portNum = 10000;
//			portList.add(portNum);
//		}
//		else if (portList.get(0) > 10000) {
//			portList.add(0, portList.get(0) - 1);
//			portNum = portList.get(0);
//		}
//		else if (portList.size() == 1) {
//			portList.add(10001);
//			portNum = portList.get(1);
//		}
//		else {
//			if (portList.size() < 50000 && portList.size() >= 2) {
//				for (int i = 1; i < portList.size(); i++) {
//					if (portList.get(i) - portList.get(i - 1) != 1) {
//						portList.add(i, portList.get(i - 1) + 1);
//						portNum = portList.get(i + 1);
//						break;
//					}
//					if (i == portList.size() - 1) {
//						portList.add(portList.get(portList.size() - 1));
//						portNum = portList.get(portList.size() - 1);
//					}
//				}
//			}
//			else {
//				System.out.println("더 이상 포트번호를 할당할 수 없음.");
//			}
//		}
//		return portNum;
//	}

	public synchronized void returnPort(int portNum) {
		System.out.println(portList.contains(portNum));
		if (portList.contains(portNum)) {
			portList.remove(portList.indexOf(portNum));
		}
		else {
			System.out.println("해당하는 포트번호가 없습니다.");
		}
	}
}
