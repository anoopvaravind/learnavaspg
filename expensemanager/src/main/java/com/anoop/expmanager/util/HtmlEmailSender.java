package com.anoop.expmanager.util;


import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/30/17
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlEmailSender {

    public static void main(String[] args) /*throws MessagingException */{

      /*  Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("spring.mail.properties.mail.smtp.socketFactory.port","25");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("anooparavindmbit","ithanechetta*#");
                    }
                });

       // try {

//            Message message = new MimeMessage(session);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("anooparavindmbit@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("a4anooparavind@gmail.com"));
            message.setSubject("Testing SSL Subject");
            message.setText("", "utf-8", "html");
        message.setContent("<html></html>", "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");*/
    //}
}
}
