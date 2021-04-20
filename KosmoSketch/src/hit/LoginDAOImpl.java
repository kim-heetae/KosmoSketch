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
	// 닉네임 가져오기
	public String checknickName(String user_id) {
		sqlSession = sqlSessionFactory.openSession();
		sqlSession.selectOne("getNickName", ldto);
		String loginChecknickName = ldto.getnickname();
		return loginChecknickName;
	}
	// 로그인 체크
	@Override
		public String checkPw(String user_id, String password) {
		sqlSession = sqlSessionFactory.openSession();
		ldto.setuser_Id(user_id);
		ldto.setpassword(password);
		sqlSession.selectOne("proc_login", ldto);
		String loginCheckMsg = ldto.getResult();
		return loginCheckMsg;
	}
	public LoginDAOImpl() {
		sqlSessionFactory = MyBatisCommonFactory.getInstance();
		ldto = new LoginDTO();
	}
	public static void main(String[] args) {
		new LoginDAOImpl();
	}
}
