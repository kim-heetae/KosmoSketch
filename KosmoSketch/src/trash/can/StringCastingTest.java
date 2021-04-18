package trash.can;

public class StringCastingTest {

	public static void main(String[] args) {
		A a = new A();
		String a_juso = a.toString();
		Object yogi = a_juso;
		A yogi2 = (A)yogi;
		System.out.println(yogi2.getClass());
		yogi2.method();
		// 결론; String을 다시 주소번지로 변환할 수는 없는 듯..
	}

}
