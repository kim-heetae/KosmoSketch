package yj;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailCheck {
    public static void main(String[] args) {
       //stamp 서버명
        String host = "smtp.naver.com";
        //네이버 아이디
		String user = "kssd6008";
		//비밀번호
		String password = "";
		//SMTP 서버 정보 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");



		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			//이메일 인증
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("@naver.com"));
			
			//메일 제목
			message.setSubject("이메일 인증!!");
		
			//메일 내용
			message.setText("이메일 인증이 확인! ");
			
			//메시지 전송
			Transport.send(message);
			System.out.println("이메일 인증확인 성공하셨습니다");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
