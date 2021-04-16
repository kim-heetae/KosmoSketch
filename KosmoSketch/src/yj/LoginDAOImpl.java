package yj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import hit.MyBatisCommonFactory;


public class LoginDAOImpl implements LoginDAO {

	// mybatis의 SqlSession 객체를 주입.
	SqlSession sqlSession;
	SqlSessionFactory sqlSessionFactory = null;

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

	// 로그인 체크
	@Override
//	public String checkPw(String user_id, String password, String nickname, String email) {
		public String checkPw(String user_id, String password) {
//		boolean result = false;
		// mapper에 2개 이상의 자료를 전달할 때 : map, dto 사용
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("password", password);
		LoginDTO ldto = new LoginDTO();
		ldto.setuser_Id(user_id);
		ldto.setpassword(password);
//		String count = sqlSession.selectOne("member.checkPw", map);
		String count = sqlSession.selectOne("proc_login", ldto);
		// 비번이 맞으면 1=>true, 틀리면 0=>false 리턴
//		if (count == 1)
//			result = true;
		return count;
	}
	public LoginDAOImpl() {
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		sqlSession = sqlSessionFactory.openSession();
		System.out.println(this.checkPw("hit", "0329"));
	}
	public static void main(String[] args) {
		new LoginDAOImpl();
	}
}
