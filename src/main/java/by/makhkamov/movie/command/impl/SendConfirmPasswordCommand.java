package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendConfirmPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENDER_EMAIL = "moxirbek021199@gmail.com";
    private static final int RANDOM_RANGE = 1054321;
    private static final String GOOGLE_SECURITY_PASSWORD = "tapymvhauydrxfrf";
    private static int confirmPassword = 0;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userEmail = (String) request.getAttribute("email");
        String firstname = (String) request.getAttribute("firstname");
        String lastname = (String) request.getAttribute("lastname");
        HttpSession mySession = request.getSession();
        Random rand = new Random();
        confirmPassword = rand.nextInt(RANDOM_RANGE);
        logger.log(Level.INFO, "Confirm password is: " + confirmPassword);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, GOOGLE_SECURITY_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));  //-->
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));  //-->
            message.setSubject("Hi, " + firstname+" "+lastname+"!");   //-->
            message.setText("your Confirm password is: " + confirmPassword);   //-->
            Transport.send(message);   //-->
            logger.log(Level.INFO, "Confirm password successfully sent");
            request.setAttribute("message", "Confirm password is sent to your email: "+userEmail);
            mySession.setAttribute("sent_password", confirmPassword);
            mySession.setAttribute("email", userEmail);
            return new Router("/pages/main/admin/movies/confirm_password_page.jsp", Router.Type.FORWARD);
        } catch (MessagingException e) {
            logger.error("Failed in sending confirm password");
            throw new CommandException("Error in sending confirm password "+e);
        }

    }
}
