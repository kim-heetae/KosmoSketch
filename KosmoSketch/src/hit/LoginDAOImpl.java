package hit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import hit.MyBatisCommonFactory;


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
	public void insertMember(LoginDTO dto) {
		// auto commit, auto close
		sqlSession.insert("member.insertMember", dto);
	}

	// 회원 정보 수정
	@Override
	public void LoginModify(LoginDTO dto) {
		sqlSession.update("member.LoginModify", dto);
	}
	
	// 중복ID체크
	@Override
	public void checkID(String id) {
		sqlSession = sqlSessionFactory.openSession();
		ldto.setuser_Id(id);
		System.out.println(ldto.getuser_Id());
		sqlSession.selectOne("proc_check_id", ldto);
		String result = ldto.getResult();
		System.out.println(result);
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
		checkID("hit");
	}
	public static void main(String[] args) {
		new LoginDAOImpl();
	}
}
