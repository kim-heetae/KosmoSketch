package trash.can;

public class IfReturn {

	public static void main(String[] args) {
		Thisif: for (int i = 0; i < 10; i++) {
			if (i == 4) {
				System.out.println(i);
				break Thisif;
			}
			System.out.println("for문 돌아요~");
		}
	}

}
