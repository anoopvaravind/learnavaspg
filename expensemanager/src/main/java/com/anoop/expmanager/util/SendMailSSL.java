package com.anoop.expmanager.util; /**
 * Created with IntelliJ IDEA.
 * User: coolminds
 * Date: 10/18/15
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Properties;
/*
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

public class SendMailSSL {
    public static void main(String[] args) {/*
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("anooparavindmbit","cherupushpam*$");
                    }
                });

        try {

//            Message message = new MimeMessage(session);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("anooparavindmbit@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("a4anooparavind@gmail.com"));
            message.setSubject("Testing SSL Subject");
            message.setText("<p>\n" +
                    "    <strong>Ref: Sn3ALL</strong>\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    Hello……\n" +
                    "</p>\n" +
                    "<center>\n" +
                    "    <table width=\"800\" border=\"0\" cellpadding=\"1\" cellspacing=\"0\">\n" +
                    "        <colgroup>\n" +
                    "            <col width=\"798\"/>\n" +
                    "        </colgroup>\n" +
                    "        <tbody>\n" +
                    "            <tr>\n" +
                    "                <td width=\"798\">\n" +
                    "                    <center>\n" +
                    "                        <table width=\"642\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                            <colgroup>\n" +
                    "                                <col width=\"642\"/>\n" +
                    "                            </colgroup>\n" +
                    "                            <tbody>\n" +
                    "                                <tr>\n" +
                    "                                    <td width=\"642\">\n" +
                    "                                        <center>\n" +
                    "                                            <table width=\"758\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                                                <colgroup>\n" +
                    "                                                    <col width=\"428\"/>\n" +
                    "                                                    <col width=\"330\"/>\n" +
                    "                                                </colgroup>\n" +
                    "                                                <tbody>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    <strong>Dear Tutorwaves Customer,</strong>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Warm greetings to you from Team Tutorwaves!\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    At the outset, let us thank you for your continued support. You are\n" +
                    "                                                                                    absolutely important to us, and we would like to appreciate and acknowledge\n" +
                    "                                                                                    your support. Apart from the relentless effort to provide your child with\n" +
                    "                                                                                    the best supplemental education, we want to reward you further.\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                        <td rowspan=\"9\" width=\"330\">\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    <strong>\n" +
                    "                                                                                        We are excited to announce our Customer Referral Program, a great\n" +
                    "                                                                                        opportunity for you to earn attractive rewards.\n" +
                    "                                                                                    </strong>\n" +
                    "                                                                                    Here's how we can help\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    each other in this drive:\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                            <p>\n" +
                    "                                                                <strong>\n" +
                    "                                                                    <br/>\n" +
                    "                                                                </strong>\n" +
                    "                                                            </p>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\" height=\"75\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    <strong>Step 1: </strong>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Refer your friends/relatives to us\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    with their contact details.\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\" height=\"60\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    <strong>Step 2: </strong>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Tutorwaves will contact your\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    friends/relatives\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\" height=\"80\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    <strong>Step 3: </strong>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Once your friend/relative completes 10 sessions with us your will receive 3\n" +
                    "                                                                                    sessions free.\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\" height=\"81\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    Hurry Up! Before someone else can share your contacts’ details with us and\n" +
                    "                                                                                    win their reference ownership, act quickly and send us the details of your\n" +
                    "                                                                                    dear and near ones. Help them catch the wave to success, just like how you\n" +
                    "                                                                                    did!\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td width=\"428\">\n" +
                    "                                                            <center>\n" +
                    "                                                                <table width=\"410\" border=\"0\" cellpadding=\"2\" cellspacing=\"0\">\n" +
                    "                                                                    <colgroup>\n" +
                    "                                                                        <col width=\"406\"/>\n" +
                    "                                                                    </colgroup>\n" +
                    "                                                                    <tbody>\n" +
                    "                                                                        <tr>\n" +
                    "                                                                            <td width=\"406\">\n" +
                    "                                                                                <p>\n" +
                    "                                                                                    Grab the opportunity, and Catch the wave to success!\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Warm regards!\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    <strong>Team Tutorwaves</strong>\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    Catch the wave to success!\n" +
                    "                                                                                    <br/>\n" +
                    "                                                                                    <a href=\"http://www.tutorwaves.com/\"><u>www.tutorwaves.com</u></a>\n" +
                    "                                                                                </p>\n" +
                    "                                                                            </td>\n" +
                    "                                                                        </tr>\n" +
                    "                                                                    </tbody>\n" +
                    "                                                                </table>\n" +
                    "                                                            </center>\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table>\n" +
                    "                                        </center>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </center>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "        </tbody>\n" +
                    "    </table>\n" +
                    "</center>", "utf-8", "html");


            message.setText("<p>    Hello <strong>"+
                    "                firstname " +
                    "                lastname</strong>,</p>" +
                    "<p>\n" +
                    "    Thanks for your time.\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    As discussed, please find the system requirements:\n" +
                    "</p>\n" +
                    "<ul>\n" +
                    "    <li>\n" +
                    "        <p>\n" +
                    "            Desktop/ Laptop, with high speed internet\n" +
                    "        </p>\n" +
                    "    </li>\n" +
                    "    <li>\n" +
                    "        <p>\n" +
                    "            Headphone with microphone (this helps the student to focus completely on the class)\n" +
                    "        </p>\n" +
                    "    </li>\n" +
                    "    <li>\n" +
                    "        <p>\n" +
                    "Teamviewer: This enables our technical support team to troubleshoot any technical issues, faster and with less hassle for you. Please download at:            <a href=\"http://www.teamviewer.com/hi/index.aspx\">http://www.teamviewer.com/hi/index.aspx</a>\n" +
                    "        </p>\n" +
                    "    </li>\n" +
                    "</ul>\n" +
                    "<p lang=\"en-US\">\n" +
                    "    Even though not an absolute necessity, we advise to provide the child with a Pen Tablet (digital pen with a compatible mouse pad) so that the child could\n" +
                    "    write comfortably, just like how we write using a normal pen and paper. Please check the below link for the pen tablet model type:-\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "    <a\n" +
                    "        href=\"http://www.amazon.com/VISTABLET-PENPAD-GRAPHIC-TABLETACCSMAC-COMPATIBLE/dp/B003ZLRPHW/ref=sr_1_1?ie=UTF8&amp;qid=1410921037&amp;sr=8-1&amp;keywords=VisTablet+80-914W04090-000V1.0+Graphics+Tablet\"\n" +
                    "    >\n" +
                    "        http://www.amazon.com/VISTABLET-PENPAD-GRAPHIC-TABLETACCSMAC-COMPATIBLE/dp/B003ZLRPHW/ref=sr_1_1?ie=UTF8&amp;qid=1410921037&amp;sr=8-1&amp;keywords=VisTablet+80-914W04090-000V1.0+Graphics+Tablet\n" +
                    "    </a>\n" +
                    "</p>\n" +
                    "<p lang=\"en-US\">\n" +
                    "    Thank you,\n" +
                    "</p>", "utf-8", "html") ;

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }*/
    }
}