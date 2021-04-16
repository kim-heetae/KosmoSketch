package qwrqwrqwr;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class TestClientView extends JFrame implements ActionListener{
	JTextField idField = null;
	JTextField pwField = null;
	JButton loginBtn = null;
	
	TestClientView(){
		initDisplay();
	}
	
	public void initDisplay() {
		this.setLayout(new FlowLayout());
		idField = new JTextField(10);
		pwField = new JTextField(10);
		loginBtn = new JButton("로긘");
		idField.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		loginBtn.addActionListener(this);
		this.add(idField);
		this.add(pwField);
		this.add(loginBtn);
		//finally - 로그인뷰는 패널로 클래스생성해준 후 로그인뷰의 생성자에 this.client = client 해줘서 client.setsize부터 새로 배치해준다
		//모든 패널이 마찬가지
		//acitonperformed는 각 패널에서 각자 재정의 해준다.
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestClientView();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == loginBtn) {
			new ClientTest(this);
		}
	}
}
