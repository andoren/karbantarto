package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidTitleException;

public class Work {
    private int Id;
    private String Title;
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
}
