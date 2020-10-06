package hu.otemplom.karbantarto.controller.email;

import hu.otemplom.karbantarto.model.Work;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.Collection;
import java.util.List;

public interface EmailService {
    void newWorkWasAddedNotifyJanitors(List<String> janitorsEmail, Work work) throws InterruptedException, MessagingException;
    void workHasBeenMarkedAsDoneNotifyAreaBoss(Work work) throws InterruptedException;
    void workHasBeenRejectedNotifyTheProperJanitor(Work work) throws InterruptedException;
    void workHasBeenAccaptedNotifyTheProperJanitor(Work work) throws InterruptedException;

}
