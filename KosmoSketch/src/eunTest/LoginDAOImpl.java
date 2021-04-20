package eunTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import eunTest.MyBatisCommonFactory;


public class LoginDAOImpl implements LoginDAO {

	// mybatis의 SqlSession 객체를 주입.
	SqlSession sqlSession = null;
	SqlSessionFactory sqlSessionFactory = null;
	LoginDTO ldto = null;

	// 회원 목록
	@Override
	public List<LoginDTO> LoginList() {
		return sqlSession.selectOne("member.LoginList");
	}

	// 회원 가입
	@Override
	public void insertMember(String id, String pw, String nickname, String email) {
		ldto.setuser_Id(id);
		ldto.setpassword(pw);
		ldto.setnickname(nickname);
		ldto.setemail(email);
		sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("join", ldto);
	}

	// 회원 정보 수정
	@Override
	public void LoginModify(LoginDTO dto) {
		sqlSession.update("member.LoginModify", dto);
	}

	// 로그인 체크
	@Override
		public String checkPw(String user_id, String password) {
		sqlSession = sqlSessionFactory.openSession();
		ldto.setuser_Id(user_id);
		ldto.setpassword(password);
		System.out.println("aaaaaa");
		sqlSession.selectOne("proc_login", ldto);
		System.out.println("aaaaaa");
		String loginCheckMsg = ldto.getResult();
		System.out.println("aaaaaa");
		return loginCheckMsg;
	}
	public LoginDAOImpl() {
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		ldto = new LoginDTO();
//		System.out.println(checkPw("hit", "0329"));
	}
//	public static void main(String[] args) {
//		new LoginDAOImpl();
//	}
}
