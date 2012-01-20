package pl.dirsot.bets.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("unused")
public class Mail {

	public Mail() {

	}

	public boolean sendActivactionMail(String toAddress,String kod,String login) throws UnsupportedEncodingException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Witamy na stronie zaklady.dirsot.pl.\n" +
				"Aktywuj konto klikając na podany link " +
				"http://zaklady.dirsot.pl/activateAccount?login="+login+"kod="+kod;

		try {
			Message msg = new MimeMessage(session);
			msg.setHeader("Content-Type", "text/plain;charset=UTF-8");
			msg.setFrom(new InternetAddress("dirsot@gmail.com","Aktywacja konta"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			msg.setSubject("Kod aktywujący");
			msg.setText(msgBody);
			Transport.send(msg);
			System.err.println("mail poszedl na "+ toAddress);
		} catch (Exception e) {
			System.err.println("mail nie poszedl");
			System.err.println(e);
			return false;
		}
		return true;
	}
}
