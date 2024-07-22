/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author ADMIN
 */
public class MailService {
//        final String username = "dat.nt271102@gmail.com";
//        final String password = "jjdicrlyxhlfutqg";

    public static final String username = "huysugar123@gmail.com";
    public static final String password = "dpmtgtmguxwknswr";

    public static void sendOtpToMail(String email, String otp) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("VERIFY YOUR OTP FROM FOOTBALL LEAGUEGA"); // Email subject

            // Email content with enhanced design
            String htmlBody = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".header { color: #007bff; }"
                    + ".otp { color: #28a745; }"
                    + ".footer { margin-top: 20px; text-align: center; color: #6c757d; }"
                    + ".content { color: #343a40; }"
                    + "</style></head>"
                    + "<body>"
                    + "<h1 class='header'>VERIFY YOUR OTP FROM VIVATIMESHARE</h1>"
                    + "<p class='content'>Your OTP is: <strong class='otp'>" + otp + "</strong></p>"
                    + "<p class='content'>Please enter this code on the website to complete the verification process.</p>"
                    + "<p class='footer'>Thank you from VivaTimeshare.</p>"
                    + "</body></html>";

            message.setContent(htmlBody, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMailWithConfirmLink(String email, String link) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("VERIFY YOUR OTP FROM VIVATIMESHARE"); // Email subject

            // Email content with enhanced design
            String htmlBody = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".header { color: #007bff; }"
                    + ".otp { color: #28a745; }"
                    + ".footer { margin-top: 20px; text-align: center; color: #6c757d; }"
                    + ".content { color: #343a40; }"
                    + "</style></head>"
                    + "<body>"
                    + "<h1 class='header'>VERIFY YOUR ACCOUNT FROM FOOTBALL LEAGUEGA</h1>"
                    + "<p class='content'>Confirm herer <a class='otp' href='" + link + "'>Click here</a></p>"
                    + "<p class='content'>Please click this link to complete the verification process.</p>"
                    + "<p class='footer'>Thank you from FOOTBALL LEAGUEGA.</p>"
                    + "</body></html>";

            message.setContent(htmlBody, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void sendMailWithInfo(String email, String Info) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("GrammarGuradian Trasition"); // Email subject

            // Email content with enhanced design
            String htmlBody = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".header { color: #007bff; }"
                    + ".otp { color: #28a745; }"
                    + ".footer { margin-top: 20px; text-align: center; color: #6c757d; }"
                    + ".content { color: #343a40; }"
                    + "</style></head>"
                    + "<body>"
                    + "<h1 class='header'>Wallet Transistion</h1>"
                    + "<p class='content'></p>"
                    + "<p class='content'> " + Info +" </p>"
                    + "<p class='footer'>Thanks you.</p>"
                    + "</body></html>";

            message.setContent(htmlBody, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
