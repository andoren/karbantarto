package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Work.*;

import javax.persistence.*;
import java.util.Date;


public class Work {

    public Work(){

    }
    public Work(int id) throws InvalidIdException {
        setId(id);
    }
    public Work(int id, String title, String description, User owner,Date createdDate ) throws InvalidIdException, InvalidCreationDateException, InvalidDescriptionException, InvalidTitleException, InvalidOwnerException {
        this(id);
        setTitle(title);
        setDescription(description);
        setCreatedDate(createdDate);
        setOwner(owner);
    }
    public Work(int id, String title, String description, User worker, User owner, Date createdDate) throws InvalidIdException, InvalidOwnerException, InvalidTitleException, InvalidDescriptionException, InvalidCreationDateException, InvalidWorkerException {
        this(id,title,description,owner,createdDate);
        setWorker(worker);
    }
    public Work(int id, String title, String description, User worker, User owner, Date createdDate, Date proceedDate) throws InvalidDescriptionException, InvalidIdException, InvalidWorkerException, InvalidTitleException, InvalidProceedDateException, InvalidOwnerException, InvalidCreationDateException {
        this(id,title,description,worker,owner,createdDate);
        setProceedDate(proceedDate);
    }
    public Work(int id, String title, String description, User worker, User owner, Date createdDate, Date proceedDate, Date doneDate) throws InvalidDescriptionException, InvalidIdException, InvalidWorkerException, InvalidTitleException, InvalidProceedDateException, InvalidOwnerException, InvalidCreationDateException, InvalidDoneDateException {
        this(id,title,description,worker,owner,createdDate,proceedDate);
        this.setDoneDate(doneDate);
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    @Column(name = "title")
    private String Title;
    @Column(name = "description")
    private String Description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area")
    private Area area;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker")
    private User Worker;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User Owner;
    @Column(name = "created_date")
    private Date CreatedDate;
    @Column(name = "proceed_date")
    private Date ProceedDate;
    @Column(name = "done_date")
    private Date DoneDate;

    public int getId() {
        return Id;
    }

    public void setId(int id) throws InvalidIdException {
        if(id < 1 ) throw new InvalidIdException("The Id cannot less then 1. Please check again. The given number is: "+id);
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) throws InvalidTitleException {
        if(title == null) throw new InvalidTitleException("The title cannot be null. Please check again.");
        else if (title.trim().length() == 0) throw new InvalidTitleException("The title cannot be full of white spaces. ");
        else if (title.length() < 10 ) throw new InvalidTitleException("The title cannot be less than 10. The given title is: "+title);
        else if(title.length() > 100) throw  new InvalidTitleException("The title cannot be more than 100. The given title is: "+title);
        else{
            Title = title;
        }
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) throws InvalidDescriptionException {
        if(description == null) throw new InvalidDescriptionException("The description cannot be null. Please check again.");
        else if(description.trim().length() == 0) throw new InvalidDescriptionException("The description cannot be full of whitespaces. Please give normal description.");
        else if (description.length() < 20) throw new InvalidDescriptionException("The description is too short.(min: 20). The given description length is: "+description.length());
        else if (description.length() > 1000) throw new InvalidDescriptionException("The description is too long.(max: 1000). The given description length is: "+description.length());
        else Description = description;
    }
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    public User getWorker() {
        return Worker;
    }

    public void setWorker(User worker) throws InvalidWorkerException {
        if(worker == null) throw new InvalidWorkerException();
        else if (worker.getRole() == Role.Janitor)
        Worker = worker;
        else throw new InvalidWorkerException();
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) throws InvalidOwnerException {
        if(owner == null) throw new InvalidOwnerException("Owner cannot be null. Please check again");
        else if (owner.getRole() == Role.Janitor) throw new InvalidOwnerException("Owner cannot be a janitor.");
        else Owner = owner;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) throws InvalidCreationDateException {
        if(createdDate == null) throw new InvalidCreationDateException("The creation date cannot be null. Please check agin.");
        CreatedDate = createdDate;
    }

    public Date getProceedDate() {
        return ProceedDate;
    }

    public void setProceedDate(Date proceedDate) throws InvalidProceedDateException {
        if(proceedDate == null) throw new InvalidProceedDateException("The proceed date cannot be null. Please check again.");
        else if(proceedDate.compareTo(getCreatedDate())< 0 ) throw new InvalidProceedDateException("The proceed date cannot be before the creation date. Please check again.. Please check again. The given name is: "+proceedDate.toString());
        else ProceedDate = proceedDate;
    }

    public Date getDoneDate() {
        return DoneDate;
    }

    public void setDoneDate(Date doneDate) throws InvalidDoneDateException {
        if(doneDate == null) throw new InvalidDoneDateException("The done date cannot be null. Please check again.");
        else if (doneDate.compareTo(getProceedDate())< 0) throw new InvalidDoneDateException("The done date cannot be before the proceedDate. Please check again. The given name is: "+doneDate.toString());
        else DoneDate = doneDate;
    }
    public boolean getIsDone(){
        return getDoneDate() != null;
    }
}
