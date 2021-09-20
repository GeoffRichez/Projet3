package fr.groupe1.goevent.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	

	public void sendEmail(String to, String subject,String body) {
	       SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        mailSender.send(message);
	    }
	
	public void SendConfirmationAccountCreated(String to) {
	SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("Bienvenue chez GoEvent");
    message.setText("Merci, nous vous confirmons la création de votre compte sur notre site GoEvent \n"
    		+"L'équipe de GoEvent");
    mailSender.send(message);
	}
	
	public void sendForgottenPasswordLink(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("goevent.isika@gmail.com", "Go Event");
	    helper.setTo(recipientEmail);
	     
	    String subject = "Lien pour initialiser votre mot de passe";
	     
	    String content = "<p>Bonjour,</p>"
	            + "<p>Vous avez demandé à réinitiliser votre mot de passe.</p>"
	            + "<p>Cliquez sur le lien pour changer votre mot de passe:</p>"
	            + "<p><a href=\"" + link + "\">Changer de mot de passe</a></p>"
	            + "<br>"
	            + "<p>L'équipe de GoEvent";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
	
}
