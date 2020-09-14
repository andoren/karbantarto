package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidBossException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidNameException;

public class Area {
    private int id;
    private String name;
    private User boss;

    public int getId() {
        return id;
    }

    public void setId(int id) throws InvalidIdException {
        if(id < 1) throw new InvalidIdException("The area id cannot be less than 1. The given id is :"+id);
        else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
        if(name == null) throw new InvalidNameException("The Area name cannot be null. Please check again.");
        else if(name.length() <4) throw new InvalidNameException("The Area name cannot be less then 4. Please check again. The given name is: "+name);
        else if(name.length() > 20) throw new InvalidNameException("The Area name cannot be greater then 20. Please check again. The given name is: "+name);
        else this.name = name;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) throws InvalidBossException {
        if(boss == null) throw new InvalidBossException("The boss cannot be null. Please check again.");
       else  this.boss = boss;
    }
}
