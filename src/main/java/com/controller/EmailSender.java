package com.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ManagedBean(name="con", eager = true)
public class EmailSender {

	
	private static String to;



	private static String subject;



	private static String name;
	
	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getName() {
		return name;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setName(String name) {
		this.name = name;
	}

	static String htmlContent="";
	
	
 
        static String from = "a.jaswanthkiran@gmail.com";
        static String password = "edabvmahavsoibvf"; // Your email account password

        public static String readHTMLFromFile(String file) {
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    contentBuilder.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return contentBuilder.toString();
        }

    public static void  sendHtmlEmail() {
    	htmlContent="<!DOCTYPE html>\r\n"
    			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
    			+ "<head>\r\n"
    			+ "    <title>Contact Us</title>\r\n"
    			+ "    <style>\r\n"
    			+ "        body {\r\n"
    			+ "            font-family: Arial, sans-serif;\r\n"
    			+ "            line-height: 1.6;\r\n"
    			+ "            margin: 0;\r\n"
    			+ "            padding: 20px;\r\n"
    			+ "            background-color: #f7f7f7;\r\n"
    			+ "        }\r\n"
    			+ "\r\n"
    			+ "        h1 {\r\n"
    			+ "            text-align: center;\r\n"
    			+ "            margin-bottom: 20px;\r\n"
    			+ "        }\r\n"
    			+ "\r\n"
    			+ "        .contact-info {\r\n"
    			+ "            padding: 10px;\r\n"
    			+ "            background-color: #fff;\r\n"
    			+ "            border: 1px solid #ccc;\r\n"
    			+ "        }\r\n"
    			+ "\r\n"
    			+ "        .field-label {\r\n"
    			+ "            font-weight: bold;\r\n"
    			+ "        }\r\n"
    			+ "    </style>\r\n"
    			+ "</head>\r\n"
    			+ "<body>\r\n"
    			+ "    <h1>Contact Us</h1>\r\n"
    			+ "    <div class=\"contact-info\">\r\n"
    			+ "        <p class=\"field-label\">Name:</p>\r\n"
    			+ "        <p>"+name+""
    			+ "</p>\r\n"
    			+ "\r\n"
    			+ "        <p class=\"field-label\">Email:</p>\r\n"
    			+ "        <p>"+to+""
    			+ "</p>\r\n"
    			+ "\r\n"
    			+ "        <p class=\"field-label\">Message:</p>\r\n"
    			+ "        <p> "+subject+""
    			+ "   </p>\r\n"
    			+ "        \r\n"
    			+ "        <p>Hello "+""+name+""
    			+ " we have received your mail we contact soon.</p>\r\n"
    			+ "    </div>\r\n"
    			+ "</body>\r\n"
    			+ "</html>\r\n"
    			+ "";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP mail server
        props.put("mail.smtp.port", "587"); // Replace with your SMTP mail server port

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        
    }
}

