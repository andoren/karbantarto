package hu.otemplom.karbantarto.controller.email;

import hu.otemplom.karbantarto.model.Work;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.*;

@Service
public class EmailServiceImpl implements EmailService  {

    private JavaMailSender emailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }
    @Async
    protected void sendSimpleMessageToCollection(List<String> emails, String subject, String event) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("otemplomnoreply@gmail.com", "k35Vl1o1L5");
            }
        });
        event = event += "\nEz egy automatikusan generált e-mail. Kérem erre az e-mail-re ne válaszoljon!";
        for (String email:emails) {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("otemplomnoreply@gmail.com", false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(subject);
            msg.setContent(event, "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }

    }

    @Override
    @Async
    public void newWorkWasAddedNotifyJanitors(List<String>janitors,Work work) {
        String Subject = "Egy új munka került beírásra!";
        String event = String.format("Az új munkát %s irta be. Elhelyezkedése: %s\n. A hiba rövid leírása: %s",
                work.getOwner().getFullname(),work.getArea().getName(),work.getTitle());
        try{
            sendSimpleMessageToCollection(janitors,Subject,event);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void workHasBeenMarkedAsDoneNotifyAreaBoss(Work work)  {
        List<String> areaBoss = Arrays.asList(work.getArea().getBoss().getEmail());
        String Subject = "Egy munka ellenőrzésére vár.";
        String event = String.format("A munkát %s írta be. Elhelyezkedése: %s\n. A hiba rövid leírása: %s\n Kérjük a munkát mielőbb ellenőrizze.",work.getOwner().getFullname(),work.getArea().getName(),work.getTitle());

        try{
            sendSimpleMessageToCollection(areaBoss,Subject,event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void workHasBeenRejectedNotifyTheProperJanitor(Work work)  {
        List<String> janitorsEmail = Arrays.asList(work.getWorker().getEmail());
        String Subject = "Munka elutasítva :( !";
        String event = String.format("A munkát elutasította %s. Elhelyezkedése: %s\n. A munka rövid leírása: %s",
                work.getArea().getBoss().getFullname(),work.getArea().getName(),work.getTitle());

        try{
            sendSimpleMessageToCollection(janitorsEmail,Subject,event);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void workHasBeenAccaptedNotifyTheProperJanitor(Work work) {
        List<String> janitorsEmail = Arrays.asList(work.getWorker().getEmail());
        String Subject = "Egy munkát befejezettnek nyílvánítottak! :)";
        String event = String.format("Gratulálok a %s által beírt munka el lett fogadva. Rövid leírása: %s",work.getOwner().getFullname(),work.getTitle());

        try{
            sendSimpleMessageToCollection(janitorsEmail,Subject,event);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
