package model;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public static void sendEmailSignup(String a) {
        

        String fromEmail = "dotung7733@gmail.com";
        String password = "jwobbomtynkncqet";
        String toEmmail = a;
        Properties pr = new Properties();
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587"); //TLS
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");

        //get Session
        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }

        });
//Ket noi email de gui
        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmmail));

            mess.setSubject("Register Successfuly");
            String body = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>JSP Page</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "    <td>\n"
                    + "        <table style=\"border-spacing:0;border-collapse:collapse;width:100%;margin:40px 0 20px\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "            <center>\n"
                    + "                <table\n"
                    + "                    style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "                    <tbody>\n"
                    + "                        <tr>\n"
                    + "                            <td>\n"
                    + "                                <table style=\"border-spacing:0;border-collapse:collapse;width:100%\">\n"
                    + "                                    <tbody>\n"
                    + "                                        <tr>\n"
                    + "                                            <td>\n"
                    + "\n"
                    + "                                                <h1\n"
                    + "                                                    style=\"font-weight:normal;margin:0;font-size:30px;color:#333\">\n"
                    + "                                                    <a href=\"https://thevapeclub.vn\"\n"
                    + "                                                       style=\"font-size:30px;text-decoration:none;color:#333\"\n"
                    + "                                                       target=\"_blank\"\n"
                    + "                                                       data-saferedirecturl=\"https://www.google.com/url?q=https://thevapeclub.vn&amp;source=gmail&amp;ust=1655483413010000&amp;usg=AOvVaw3mqQmYH-B0jekACwJJY5LC\">Kingsman</a>\n"
                    + "                                                </h1>\n"
                    + "\n"
                    + "                                            </td>\n"                             
                    + "                                        </tr>\n"
                    + "                                    </tbody>\n"
                    + "                                </table>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </tbody>\n"
                    + "                </table>\n"
                    + "            </center>\n"
                    + "    </td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding-bottom:40px\">\n"
                    + "    <center>\n"
                    + "        <table\n"
                    + "            style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <h2 style=\"font-weight:normal;margin:0;font-size:24px;margin-bottom:10px\">\n"
                    + "                            Đăng kí thành công!\n"
                    + "                        </h2>\n"
                    + "                        <p style=\"margin:0;color:#777;line-height:150%;font-size:16px\">\n"
                    + "                            Xin chào, bạn đã đăng kí tài khoản thành công.\n"
                    + "                            Vui lòng nhấn vào đường dẫn để quay trở lại trang web: http://localhost:9999/BooksShoppingOnline/home .\n"
                    + "                        </p>\n"
                    + "\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </tbody>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%;border-top:1px solid #e5e5e5\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding:40px 0\">\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%;border-top:1px solid #e5e5e5\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding:35px 0\">\n"
                    + "    <center>\n"
                    + "        <table\n"
                    + "            style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td style=\"\">\n"
                    + "                        <p style=\"margin:0;color:#999;line-height:150%;font-size:14px\">\n"
                    + "                            Nếu\n"
                    + "                            bạn\n"
                    + "                            có\n"
                    + "                            bất\n"
                    + "                            cứ\n"
                    + "                            câu\n"
                    + "                            hỏi\n"
                    + "                            nào,\n"
                    + "                            đừng\n"
                    + "                            ngần\n"
                    + "                            ngại\n"
                    + "                            liên\n"
                    + "                            lạc\n"
                    + "                            với\n"
                    + "                            chúng\n"
                    + "                            tôi\n"
                    + "                            tại\n"
                    + "                            <a href=\"mailto:thevapeclub@helix.com.vn\"\n"
                    + "                               style=\"font-size:14px;text-decoration:none;color:#1666a2\"\n"
                    + "                               target=\"_blank\">dotung7733@gmail.com</a>\n"
                    + "                        </p>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </tbody>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<img src=\"https://ci4.googleusercontent.com/proxy/AkPYSwbfCTPpa9UY2iemTt-8uuNCxd9wMi-MxiDXCwCclRn4IrvavPQy53Rok8pDmYePvpYw7glbcjctupZqDJjD9WVBMoR1vQ=s0-d-e1-ft#http://hstatic.net/0/0/global/notifications/spacer.png\"\n"
                    + "     height=\"0\" style=\"min-width:600px;height:0\" class=\"CToWUd\">\n"
                    + "</body>\n"
                    + "</html>";
            mess.setText(body);
            mess.setContent(body, "text/html;charset=UTF-8");
            Transport.send(mess);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailFeedback(String a) {

        String fromEmail = "dotung7733@gmail.com";
        String password = "jwobbomtynkncqet";
        String toEmmail = a;
        Properties pr = new Properties();
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587"); //TLS
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");

        //get Session
        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }

        });
//Ket noi email de gui
        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmmail));

            mess.setSubject("Received Successfully");
            String body = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>JSP Page</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "    <td>\n"
                    + "        <table style=\"border-spacing:0;border-collapse:collapse;width:100%;margin:40px 0 20px\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "            <center>\n"
                    + "                <table\n"
                    + "                    style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "                    <tbody>\n"
                    + "                        <tr>\n"
                    + "                            <td>\n"
                    + "                                <table style=\"border-spacing:0;border-collapse:collapse;width:100%\">\n"
                    + "                                    <tbody>\n"
                    + "                                        <tr>\n"
                    + "                                            <td>\n"
                    + "\n"
                    + "                                                <h1\n"
                    + "                                                    style=\"font-weight:normal;margin:0;font-size:30px;color:#333\">\n"
                    + "                                                    <a href=\"https://thevapeclub.vn\"\n"
                    + "                                                       style=\"font-size:30px;text-decoration:none;color:#333\"\n"
                    + "                                                       target=\"_blank\"\n"
                    + "                                                       data-saferedirecturl=\"https://www.google.com/url?q=https://thevapeclub.vn&amp;source=gmail&amp;ust=1655483413010000&amp;usg=AOvVaw3mqQmYH-B0jekACwJJY5LC\">Kingsman</a>\n"
                    + "                                                </h1>\n"
                    + "\n"
                    + "                                            </td>\n"                             
                    + "                                        </tr>\n"
                    + "                                    </tbody>\n"
                    + "                                </table>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </tbody>\n"
                    + "                </table>\n"
                    + "            </center>\n"
                    + "    </td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding-bottom:40px\">\n"
                    + "    <center>\n"
                    + "        <table\n"
                    + "            style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <h2 style=\"font-weight:normal;margin:0;font-size:24px;margin-bottom:10px\">\n"
                    + "                            Cám ơn bạn đã mua hàng!\n"
                    + "                        </h2>\n"
                    + "                        <p style=\"margin:0;color:#777;line-height:150%;font-size:16px\">\n"
                    + "                            Xin chào, bạn đã nhận được đơn đặt hàng thành công.\n"
                    + "                            Nếu bản hài lòng với sản phẩm nhận được, chúng tôi mong bạn sẽ để lại cho shop vài đánh giá.\n"
                    + "                        </p>\n"
                    + "\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </tbody>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%;border-top:1px solid #e5e5e5\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding:40px 0\">\n"
                    + "    <center>\n"
                    + "        <h3 style=\"font-weight:normal;margin:0;font-size:20px;margin-bottom:25px\">\n"
                    + "            Hướng dẫn đánh giá\n"
                    + "        </h3>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-2.fna.fbcdn.net/v/t1.15752-9/361693622_786982496301229_4914403781285728190_n.png?_nc_cat=102&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=hT4N03nWUvgAX8qW4Ld&_nc_ht=scontent.fhan5-2.fna&oh=03_AdQkIgvh13LdejZlbpCgxSqh8ZgGG1WbF4pxfur2dsyHxA&oe=64DDD0DF\"><br><hr><br>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-8.fna.fbcdn.net/v/t1.15752-9/361678656_303772282109459_6132116405992800903_n.png?_nc_cat=110&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=WMTV8cs-dN0AX8V3ZBP&_nc_ht=scontent.fhan5-8.fna&oh=03_AdSEoTd7S9u0mH_qFqSBDydaP7V9tMGHuIe5MhzyvTyXcQ&oe=64DDD80F\"><br><hr><br>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-10.fna.fbcdn.net/v/t1.15752-9/361561802_1830663414057438_873362520027107886_n.png?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=LROamDZQb6sAX-ev5p-&_nc_ht=scontent.fhan5-10.fna&oh=03_AdTQtxOgVnvN9PpPZd1iWcRUzkEqsA5h2mUbi7rCdjw8Fw&oe=64DDF1CF\"><br><hr><br>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-10.fna.fbcdn.net/v/t1.15752-9/361552926_666810448658840_3425501771490053094_n.png?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=1UwF45EZWl8AX-pXpXP&_nc_ht=scontent.fhan5-10.fna&oh=03_AdRgAOzygtgA9fOzYtUqNH7FsOOJ7uHqok5Z7vrL-unBlQ&oe=64DDF097\"><br><hr><br>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-2.fna.fbcdn.net/v/t1.15752-9/361582071_999454514581555_5906414826851153920_n.png?_nc_cat=105&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=A1iKdXW9ZeoAX--djOa&_nc_ht=scontent.fhan5-2.fna&oh=03_AdRIeJn112MmkSFXT6J74RKmbmuiDsnwTeOZRV-5ZN4gZA&oe=64DDC85D\"><br><hr><br>\n"
                    + "                        <img style=\"max-width: 70%; padding-bottom: 2%\" src=\"https://scontent.fhan5-8.fna.fbcdn.net/v/t1.15752-9/361686722_1442129019946739_7230712222261309286_n.png?_nc_cat=108&cb=99be929b-59f725be&ccb=1-7&_nc_sid=ae9488&_nc_ohc=_COakFVK0jgAX-QpogT&_nc_ht=scontent.fhan5-8.fna&oh=03_AdTOUCoFnUwCAcla3WjPV1z3TdxcoSUPnPklgEv1cNuvNw&oe=64DDDE52\">"
                    + "    </center>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%;border-top:1px solid #e5e5e5\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding:40px 0\">\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table style=\"border-spacing:0;border-collapse:collapse;width:100%;border-top:1px solid #e5e5e5\">\n"
                    + "    <tbody>\n"
                    + "        <tr>\n"
                    + "            <td style=\"padding:35px 0\">\n"
                    + "    <center>\n"
                    + "        <table\n"
                    + "            style=\"border-spacing:0;border-collapse:collapse;width:560px;text-align:left;margin:0 auto\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td style=\"\">\n"
                    + "                        <p style=\"margin:0;color:#999;line-height:150%;font-size:14px\">\n"
                    + "                            Nếu\n"
                    + "                            bạn\n"
                    + "                            có\n"
                    + "                            bất\n"
                    + "                            cứ\n"
                    + "                            câu\n"
                    + "                            hỏi\n"
                    + "                            nào,\n"
                    + "                            đừng\n"
                    + "                            ngần\n"
                    + "                            ngại\n"
                    + "                            liên\n"
                    + "                            lạc\n"
                    + "                            với\n"
                    + "                            chúng\n"
                    + "                            tôi\n"
                    + "                            tại\n"
                    + "                            <a href=\"mailto:thevapeclub@helix.com.vn\"\n"
                    + "                               style=\"font-size:14px;text-decoration:none;color:#1666a2\"\n"
                    + "                               target=\"_blank\">dotung7733@gmail.com</a>\n"
                    + "                        </p>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </tbody>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<img src=\"https://ci4.googleusercontent.com/proxy/AkPYSwbfCTPpa9UY2iemTt-8uuNCxd9wMi-MxiDXCwCclRn4IrvavPQy53Rok8pDmYePvpYw7glbcjctupZqDJjD9WVBMoR1vQ=s0-d-e1-ft#http://hstatic.net/0/0/global/notifications/spacer.png\"\n"
                    + "     height=\"0\" style=\"min-width:600px;height:0\" class=\"CToWUd\">\n"
                    + "</body>\n"
                    + "</html>";
            mess.setText(body);
            mess.setContent(body, "text/html;charset=UTF-8");
            Transport.send(mess);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SendMail sm = new SendMail();
        sm.sendEmailSignup("kiet9cvl@gmail.com");
//        sm.sendEmailFeedback("kiet9cvl@gmail.com");
    }

    public static void send(String smtpServer, String to, String from, String psw,
            String subject, String body) throws Exception {
// java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Properties props = System.getProperties();
// –
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        final String login = from;//”nth001@gmail.com”;//usermail
        final String pwd = psw;//”password cua ban o day”;
        Authenticator pa = null; //default: no authentication
        if (login != null && pwd != null) { //authentication required?
            props.put("mail.smtp.auth", "true");
            pa = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(login, pwd);
                }
            };
        }//else: no authentication
        Session session = Session.getInstance(props, pa);
// — Create a new message –
        Message msg = new MimeMessage(session);
// — Set the FROM and TO fields –
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                to, false));

// — Set the subject and body text –
        msg.setSubject(subject);
        msg.setText(body);
// — Set some other header information –
        msg.setHeader("X - Mailer", "LOTONtechEmail");
        msg.setSentDate(new java.util.Date());
        msg.setContent(body, "text/html;charset=UTF-8");
        msg.saveChanges();
// — Send the message –
        Transport.send(msg);
        System.out.println(
                "Message sent OK.");

    }

}
