package space.vakar.stuff.email.impl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.vakar.stuff.email.EmailException;
import space.vakar.stuff.email.api.EmailService;
import space.vakar.stuff.email.model.Email;

@Service
public class EmailServiceImp implements EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImp.class);

  private static final String EMAIL_CONTENT_TYPE = "text/html";

  private Session session;
  private String fromEmail;

  @Autowired
  private EmailServiceImp(Session session, String fromEmail) {
    this.session = session;
    this.fromEmail = fromEmail;
  }

  @Override
  public void sendEmail(Email email) {
    Message mimeMessage = new MimeMessage(session);
    try {
      mimeMessage.setFrom(new InternetAddress(fromEmail));
      mimeMessage.setRecipients(
          Message.RecipientType.TO, InternetAddress.parse(email.getRecipientEmail()));
      mimeMessage.setSubject(email.getSubject());
      mimeMessage.setContent(getMultipartBody(email.getMessage()));
      Transport.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("An error occurred while sending email: {}", email);
      throw new EmailException("An error occurred while sending email", e);
    }
  }

  private Multipart getMultipartBody(String message) throws MessagingException {
    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    Multipart multipart = new MimeMultipart();
    mimeBodyPart.setContent(message, EMAIL_CONTENT_TYPE);
    multipart.addBodyPart(mimeBodyPart);
    return multipart;
  }
}
