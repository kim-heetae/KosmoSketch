package eunTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CheckDAO {
	
	// mybatis의 SqlSession 객체를 주입.
	SqlSession sqlSession = null;
	SqlSessionFactory sqlSessionFactory = null;
	
	CheckDTO cdto = null;
	
	//생성자
	public CheckDAO() {
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		cdto = new CheckDTO();
	}
	
	/*****************************************************
	 * 닉네임의 중복여부를 검토하는 메소드
	 * @param nickname 중복여부 검사를 하고자 하는 닉네임
	 * @return 중복된 닉네임인 경우 "이미 존재하는 닉네임입니다.", 중복되지 않은 닉네임인 경우 "사용 가능한 닉네임입니다."라는 메세지를 반환한다. 
	 *****************************************************/
	public String isDuplicatedNickname(String nickname) {
		sqlSession = sqlSessionFactory.openSession();
		cdto.setNickname(nickname);
		sqlSession.selectOne("proc_check_nic", cdto);
		String result = cdto.getResult_msg();
		sqlSession.close();
		return result;
	}
	
	/*****************************************************
	 * 아이디의 중복여부를 검토하는 메소드
	 * @param p_id 중복여부 검사를 하고자 하는 아이디
	 * @return 중복된 아이디인 경우 "존재하는 아이디입니다.", 중복되지 않은 아이디인 경우 "사용가능한 아이디입니다."라는 메세지를 반환한다. 
	 *****************************************************/
	public String isDuplicatedID(String p_id) {
		sqlSession = sqlSessionFactory.openSession();
		cdto.setID(p_id);
		sqlSession.selectOne("proc_check_id", cdto);
		String result = cdto.getResult_msg();
		sqlSession.close();
		return result;
	}

	public static void main(String[] args) {
		CheckDAO cdao = new CheckDAO();
		// 닉네임 단위 테스트
//		System.out.println(cdao.isDuplicatedNickname("은영"));
		// 아이디 단위 테스트
		System.out.println(cdao.isDuplicatedID("hit"));
	}

}
