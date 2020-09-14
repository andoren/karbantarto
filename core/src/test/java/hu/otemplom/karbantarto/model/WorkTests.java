package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hu.otemplom.karbantarto.model.Exceptions.Work.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTests {
    Work work;

    @Before
    public void init(){
        work = new Work();
    }

    @Test
    public void setValidId() throws InvalidIdException {
        work.setId(1);
        Assert.assertEquals(1,work.getId());
    }
    @Test (expected = InvalidIdException.class)
    public void setIdToZero() throws InvalidIdException {
        work.setId(0);
    }
    @Test(expected = InvalidIdException.class)
    public void setIdToNegativeNumber() throws InvalidIdException {
        work.setId(-1);
    }
    @Test
    public void setValidTitle() throws InvalidTitleException {
        String expected = "Ez egy valid title";
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTitleToNull() throws InvalidTitleException {
        String expected = null;
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTitleToWhiteSpaces() throws InvalidTitleException {
        String expected = "               ";
        work.setTitle(expected);
        Assert.assertEquals(expected,work.getTitle());
    }
    @Test(expected = InvalidTitleException.class)
    public void setTooShortTitle() throws InvalidTitleException {
        work.setTitle("123456789");
    }
    @Test(expected = InvalidTitleException.class)
    public void setTooLongTitle() throws InvalidTitleException{
        work.setTitle("123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789");
    }
    @Test
    public void setValidDescription() throws InvalidDescriptionException{
        String expected = "Elromlott az Demens 123-as szobábana wc lehúzójának a kiscicájának az izébizéje.";
        work.setDescription(expected);
        Assert.assertEquals(expected,work.getDescription());
    }
    @Test(expected = InvalidDescriptionException.class)
    public void setNullDescription() throws InvalidDescriptionException{
        String expected = null;
        work.setDescription(expected);

    }
    @Test(expected = InvalidDescriptionException.class)
    public void setWhitespacesDescription() throws InvalidDescriptionException{
        String expected = "                                                        ";
        work.setDescription(expected);

    }
    @Test(expected = InvalidDescriptionException.class)
    public void setTooShortDescriptionException() throws InvalidDescriptionException{
        String expected = "Ez egy rövid leírás";
        work.setDescription(expected);

    }
    @Test(expected = InvalidDescriptionException.class)
    public void setTooLongDescriptionException() throws InvalidDescriptionException{
        String expected = "Ez egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírásEz egy rövid leírás";

        work.setDescription(expected);

    }
    @Test
    public void setValidWorker() throws InvalidRoleException, InvalidWorkerException {
        User worker = new User();
        worker.setRole(Role.Janitor);
        work.setWorker(worker);
        Assert.assertEquals(worker,work.getWorker());
    }
    @Test(expected = InvalidWorkerException.class)
    public void setNullWorker() throws InvalidWorkerException, InvalidRoleException {
        User worker = null;
        work.setWorker(worker);

    }
    @Test(expected = InvalidWorkerException.class)
    public void setNotJanitorWorker() throws InvalidWorkerException, InvalidRoleException {
        User worker = new User();
        worker.setRole(Role.User);
         work.setWorker(worker);

    }
    @Test
    public void setOwner() throws InvalidOwnerException {
        User owner = new User();
        work.setOwner(owner);
        Assert.assertEquals(owner,work.getOwner());
    }
    @Test(expected = InvalidOwnerException.class)
    public void setNullOwner() throws InvalidOwnerException {
        User owner = null;
        work.setOwner(owner);

    }
    @Test(expected = InvalidOwnerException.class)
    public void setOwnerAsJanitor() throws InvalidOwnerException, InvalidRoleException {
        User owner = new User();
        owner.setRole(Role.Janitor);
        work.setOwner(owner);

    }
    @Test
    public void setOwnerAsNotJanitor() throws InvalidOwnerException, InvalidRoleException {
        User owner = new User();
        owner.setRole(Role.User);
        work.setOwner(owner);

    }
    @Test
    public void setValidCreationDate() throws InvalidCreationDateException {
        Date date = new Date();
        work.setCreatedDate(date);
        Assert.assertEquals(date,work.getCreatedDate());
    }
    @Test(expected = InvalidCreationDateException.class)
    public void setNullCreationDate() throws InvalidCreationDateException {
        Date date = null;
        work.setCreatedDate(date);

    }
    @Test
    public void setValidProceedDate() throws InvalidCreationDateException, InvalidProceedDateException {
        Date creation = new Date();
        Date proceedDate = new Date();
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);
        Assert.assertEquals(proceedDate,work.getProceedDate());
    }
    @Test(expected = InvalidProceedDateException.class)
    public void setNullProceedDate() throws InvalidCreationDateException, InvalidProceedDateException {
        Date creation = new Date();
        Date proceedDate = null;
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);

    }
    @Test(expected = InvalidProceedDateException.class)
    public void setProceedDateLessThanCreatedDate() throws InvalidCreationDateException, InvalidProceedDateException, ParseException {
        Date creation = new Date();
        Date proceedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24");
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);

    }
    @Test
    public void setValidDoneDate() throws InvalidDoneDateException,InvalidCreationDateException, InvalidProceedDateException {
        Date creation = new Date();
        Date proceedDate = new Date();
        Date doneDate = new Date();
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);
        work.setDoneDate(doneDate);
        Assert.assertEquals(doneDate,work.getDoneDate());
    }
    @Test(expected = InvalidDoneDateException.class)
    public void setNullDoneDate() throws InvalidDoneDateException,InvalidCreationDateException, InvalidProceedDateException {
        Date creation = new Date();
        Date proceedDate = new Date();
        Date doneDate = null;
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);
        work.setDoneDate(doneDate);

    }
    @Test(expected = InvalidDoneDateException.class)
    public void setdoneDateLessThenProceedDateDoneDate() throws InvalidDoneDateException, InvalidCreationDateException, InvalidProceedDateException, ParseException {
        Date creation = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24");
        Date proceedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-27");
        Date doneDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-25");
        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);
        work.setDoneDate(doneDate);

    }

    @Test
    public void getIsDoneFlase() throws InvalidDoneDateException, InvalidCreationDateException, InvalidProceedDateException, ParseException {
        Date creation = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-24");
        Date proceedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-24");

        work.setCreatedDate(creation);
        work.setProceedDate(proceedDate);

        Assert.assertEquals(false,work.getIsDone());

    }
}
