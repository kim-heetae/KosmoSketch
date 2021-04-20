package hit;

// Server
public class LoopTest_3 extends Thread {
	public LoopTest_3() {
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("돌아라");
			
		}
	}
}
