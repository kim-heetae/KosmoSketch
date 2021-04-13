package qwrqwrqwr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class ClientEvent implements ActionListener, MouseListener, MouseMotionListener{
	
	Client client = null;
	
	public ClientEvent() {
		
	}
	
	public ClientEvent(Client client) {
		this.client = client;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String msg = "";
		if(ae.getSource() == client.jbtn_insert) {
			msg = client.jtf_chat.getText().trim();
			client.jtf_chat.setText("");
			client.jta_log.append(msg+"\n");
		}
	}
	
}
