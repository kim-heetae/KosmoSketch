package hit;

import java.util.List;

public interface LoginDAO {
	// 회원목록
	public List<LoginDTO> LoginList();

	// 회원가입
	public void insertMember(LoginDTO dto);

	// 회원정보수정
	public void LoginModify(LoginDTO dto);
	// 중복ID 체크
	public void checkID(String id);
	// 로그인
//	public String checkPw(String user_id, String password, String nickname, String email);
	public String checkPw(String user_id, String password);
}
