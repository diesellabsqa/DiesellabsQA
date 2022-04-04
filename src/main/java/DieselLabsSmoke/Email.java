package DieselLabsSmoke;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Email {



    public  String username="praveena.johnbose@capestart.com";
    public  String password="btcdptwemamksvxn" ;
    public String file ="Email_Report";
    
    
    
  public static void sendEmail(final String username, final String password,String filename)  {

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {
    	
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from.praveena.johnbose@capestart.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("dhaivat@diesellabs.com,lihua@diesellabs.com,akash.k@capestart.com,praveena.johnbose@capestart.com"));
        message.setSubject("SmokeTestReport");
        message.setText("Please find the smoke test report as attached");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
      
        //String file = System.getProperty("Email_Report");
        String fileName = "emailable-report.html";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");
    

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
    }
  }
