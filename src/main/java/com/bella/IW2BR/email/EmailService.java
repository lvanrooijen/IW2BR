package com.bella.IW2BR.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/** This class handles sending emails */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  /**
   * basic method to send an email
   *
   * @param to email address of the recipient
   * @param subject subject of the email
   * @param text body of the email
   */
  public void sendMail(String to, String username, String subject, String text)
      throws MessagingException {
    Context context = new Context();
    context.setVariable("username", username);
    context.setVariable("text", text);
    context.setVariable("year", LocalDate.now().getYear());

    String body = templateEngine.process("BasicEmail.html", context);

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(body, true);
    mailSender.send(message);
  }

  /**
   * Sends registration email to the user
   *
   * @param to email address of the recipient
   * @param username name of the recipient
   * @throws MessagingException when something goes wrong
   */
  public void sendUserRegistrationEmail(String to, String username) throws MessagingException {
    Context context = new Context();
    context.setVariable("username", username);
    context.setVariable("year", LocalDate.now().getYear());

    String body = templateEngine.process("UserRegistration.html", context);

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(String.format("Welcome %s!", username));
    helper.setText(body, true);
    mailSender.send(message);
  }
}
