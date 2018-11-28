package fr.adaming.util;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.modele.Commande;
import fr.adaming.modele.LigneCommande;

public class Mail {
	// Declaration des attributs
	private String to;
	private String from;
	private String subject;
	private String bodytext;
	private File temp;

	public Mail() {
		super();
	}

	public Mail(String to, String from, String subject, String bodytext, File temp) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.bodytext = bodytext;
		this.temp= temp;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodytext() {
		return bodytext;
	}

	public void setBodytext(String bodytext) {
		this.bodytext = bodytext;
	}

	public File getTemp() {
		return temp;
	}

	public void setTemp(File temp) {
		this.temp = temp;
	}

	public static void createEmail(Mail mail) {
		try {
	
			Properties props = new Properties();

			//PARAMETRE DU SERVEUR SMTP
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", "465");
		
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("linkoua@gmail.com", "zuarthan58");// Specify the Username and the PassWord
				}
			});

			MimeMessage email = new MimeMessage(session);

			email.setFrom(new InternetAddress(mail.from));
			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.to));
			email.setSubject(mail.subject);

	        //Ajout d'un corps HTML
			MimeMultipart multipart = new MimeMultipart("related");
	        BodyPart messageBodyPart = new MimeBodyPart();
	        String html = "<!DOCTYPE html><head><style> .email-body{ height:100%; background: #ffd89b; background: -webkit-linear-gradient(to bottom, #ffd89b, #19547b); background: linear-gradient(to bottom, #ffd89b, #19547b); background-repeat: no-repeat;}.email-content {margin: 0 auto; margin-top: 50px; width: 600px; min-height: 500px; background: #F2F2F2; border: 0px; box-shadow: 1px 1px 2px #fff inset, -1px -1px 2px #fff inset; border-radius: 6px;} .header-email {width:100%; min-height: 100px; border-radius: 6px; background: -webkit-linear-gradient(to bottom, #232526, #414345)!important;  background: linear-gradient(to bottom, #232526, #414345)!important;\r\n"
					+ "} .header-email img{" + "margin:18px;\r\n"
					+ "} .body-email { margin:  20px auto; width:80%; min-height: 300px; \r\n"
					+ "} .footer-email { width:100%; min-height: 100px; background: #232526!important; background: -webkit-linear-gradient(to top, #232526, #414345)!important; background: linear-gradient(to top, #232526, #414345)!important;\r\n"
					+ "} </style></head><html> <body class=\"email-body\"> <div class=\"email-content\"><div class=\"header-email\"><img src=\"cid:image\"></div><div class=\"body-email\">"
					+ mail.bodytext + "</div> <div class=\"footer-email\"></div></div></body></html>";
	        messageBodyPart.setContent(html, "text/html");
	        multipart.addBodyPart(messageBodyPart);
//Ajout d'un image
	        messageBodyPart = new MimeBodyPart();
	        DataSource fds1 = new FileDataSource
	  	          ("amge ");
	  	        messageBodyPart.setDataHandler(new DataHandler(fds1));
	  	    FileDataSource source = new FileDataSource(mail.temp);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setHeader("Content-ID","<image>");
	        multipart.addBodyPart(messageBodyPart);
	        
	        //Ajout du corps dans mail
	        email.setContent(multipart);
			Transport.send(email);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
