package com.dodone.dodone.service.email;

import com.dodone.dodone.entity.Todo;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class EmailService {

    private static String password;

    @Value("${EMAIL_PASSWORD}")
    public void setPassword(String password) {
        EmailService.password = password;
    }

    public static void sendMail(String recipient, Todo todo) throws MessagingException {

        try {
            Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccEmail = "dodone.enterprise@gmail.com";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccEmail, password);
                }
            });

            Message message = prepareMessage(session, myAccEmail, recipient, todo);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new MessagingException("Failed to prepare email message", e);
        }

    }

    private static Message prepareMessage(Session session, String myAccEmail, String recipient, Todo todo)
            throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Urgent Reminder: Your Task is Due in 1 Hour!");
            message.setText(buildEmailBody(todo));
            return message;
        } catch (MessagingException e) {
            throw new MessagingException("Failed to prepare email message", e);
        }
    }

    private static String buildEmailBody(Todo todo) {
        return ("Hello there! \n \n" +
                "Hope you're having a great day! This is a friendly reminder that you have an important \n" +
                "task on your to-do list that is due within the next hour. \uD83D\uDD52 \n \n" +
                "   Task: " + todo.getName() + " \n" +
                "   Due: " + todo.getDueDate() + " \n \n" +
                "Please take a moment to complete this task to stay on top of your schedule.\n" +
                "If you've already completed it, congratulations! If not, now's the perfect time to tackle it. \n \n" +
                "Remember, we're here to help you stay organized and on track. If you have any questions  \n" +
                "or need assistance, feel free to reach out. \n \n" +
                " Happy tasking! \n" +
                " DoDone Team ");
    }
}