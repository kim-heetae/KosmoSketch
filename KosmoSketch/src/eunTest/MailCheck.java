package eunTest;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailCheck {
////////////////////////////////// [[[ 보내는 계정 정보 ]]] /////////////////////////////////////////////////
	//stamp 서버명
	final String host = "smtp.naver.com";
	//네이버 아이디
	final String user = "ye7383";
	//비밀번호
	final String password = "kosmo80#";
	//보내는 이메일
	final String sendEmailAddress = "ye7383@naver.com";
	//포트번호
	final int port = 465;
	
	int code = 0;
//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////// [[[ 받는 계정 정보 선언]]] /////////////////////////////////////////////////
	//받는 계정 주소
	String receiveEmailAddress = null;
	//메일 제목
	final String subject = "KosmoCatch 인증 메일";
	//메일 내용
	String content = null;
//////////////////////////////////////////////////////////////////////////////////////////////////////
	Properties props = null;
	Session session = null;
	
////////////////////////////////// [[[ 받는 계정 정보 초기화]]] /////////////////////////////////////////////////
	public int setReceiveEmail(String receiveEmailAddress) {
		//받는 계정 주소
		this.receiveEmailAddress = receiveEmailAddress;
		//랜덤 코드 생성
		Random r = new Random();
		do {
			code = r.nextInt(1000000);			
		}while(code < 100000);
		
		//메일 내용
		content = "KosmoCatch의 인증번호는 " + code + "입니다.";
		setSMTPserver();
		sendEmail();
		return code;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setSMTPserver() {
    	//SMTP 서버 정보 설정
    	props = System.getProperties();
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", port);
    	props.put("mail.smtp.auth", true);
    	props.put("mail.smtp.ssl.enable", true); 
    	props.put("mail.smtp.ssl.trust", host);    	
    }
    
    public void sendEmail() {
    	session = Session.getDefaultInstance(props, new Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(user, password);
    		}
    	});
    	try {
    		MimeMessage message = new MimeMessage(session);
    		message.setFrom(new InternetAddress(sendEmailAddress));
    		//이메일 인증
    		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
    		
    		//메일 제목
    		message.setSubject(subject);
    		
    		//메일 내용
    		message.setText(content);
    		
    		//메시지 전송
    		Transport.send(message);
    	} catch (MessagingException e) {
    		code = 404;
    		e.printStackTrace();
    	}
    	
    }
}
