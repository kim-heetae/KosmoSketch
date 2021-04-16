package yj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDAOImpl implements LoginDAO {

	// mybatis의 SqlSession 객체를 주입.
	SqlSession sqlSession;

	// 회원 목록
	@Override
	public List<LoginDTO> LoginList() {
		return sqlSession.LoginList("member.LoginList");
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
		sqlSession.Modify("member.LoginModify", dto);
	}

	// 로그인 체크
	@Override
	public boolean checkPw(String user_id, String password, String nickname, String email) {
		boolean result = false;
		// mapper에 2개 이상의 자료를 전달할 때 : map, dto 사용
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("password", password);
		int count = sqlSession.selectOne("member.checkPw", map);
		// 비번이 맞으면 1=>true, 틀리면 0=>false 리턴
		if (count == 1)
			result = true;
		return result;
	}

}
