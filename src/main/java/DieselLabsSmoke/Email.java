package DieselLabsSmoke;

import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email {



    final String username = "praveena.johnbose@capestart.com";
    final String password = "btcdptwemamksvxn";
    
    
    
  public static void sendEmail(final String username, final String password){

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
                InternetAddress.parse("lihua@diesellabs.com,dhaivat@diesellabs.com,praveena.johnbose@capestart.com,akash.k@capestart.com"));
        message.setSubject("SmokeTestReport");
        message.setText("Please find the smoke test report as attached");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String Email_Report = System.getenv("Email_Report");
        String file = Email_Report;
        String fileName = "emailable-report.html";
        DataSource source = new FileDataSource(file);
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
