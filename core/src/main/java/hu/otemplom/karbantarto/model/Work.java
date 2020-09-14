package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Work.*;

import java.util.Date;


public class Work {
    private int Id;
    private String Title;
    private String Description;
    private User Worker;
    private User Owner;
    private Date CreatedDate;
    private Date ProceedDate;
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
