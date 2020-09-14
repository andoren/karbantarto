package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidDescriptionException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Work.InvalidTitleException;

public class Work {
    private int Id;
    private String Title;
    private String Description;
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
}
