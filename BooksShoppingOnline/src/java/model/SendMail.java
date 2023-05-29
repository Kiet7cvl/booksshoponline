package model;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class SendMail {

    public String getRandom() {
        Random r = new Random();
        int num = r.nextInt(99999999);
        return String.format("%08d", num);
    }

    public boolean sendEmailSignup(String a, String code) {
        boolean test = false;

        String fromEmail = "kieththe161307@fpt.edu.vn";
        String password = "11112012";
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

            mess.setSubject("SignUp Success");
            mess.setText("Xin chào " + a+ "\n"
                    + "Bạn đã đăng kí tài khoản thành công: "+ "\n"+"Vui lòng nhấp vào đường dẫn để quay lại trang web" + "http://localhost:8080/books-shop-online/home." + "\n"
                    + "To be able to log in, please enter the security code to activate your account."
                    + "Please do not share the code with anyone.");
            Transport.send(mess);
            test = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return test;
    }
    
    public static void sendEmailSignup(String a) {

        String fromEmail = "kieththe161307@fpt.edu.vn";
        String password = "11112012";
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

            mess.setSubject("SignUp Success");
            mess.setText("Hi " + a+ "\n"
                    + "Your account has been successfully registered "+ "\n"+"Please click on the link to return to the website: " + "\n" + "http://localhost:9999/BooksShoppingOnline/home" + "\n"
                    + "Please do not share the code with anyone.");
            Transport.send(mess);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
       
    }

    public static void main(String[] args) {
        SendMail sm = new SendMail();
        String code = sm.getRandom();
//        DAOAccount dAOAccount = new DAOAccount();
//        List<Account> lAccounts = dAOAccount.getAllListAccount();
//        Account a = lAccounts.get(1);
//        boolean test = sm.sendEmailSignup("kiet9cvl@gmail.com", code);
//        if (test) {
//            System.out.println("done");
//        } else {
//            System.out.println("failed");
//        }
          sm.sendEmailSignup("kiet9cvl@gmail.com");
    }
}
