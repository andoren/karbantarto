package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.User.*;


public class User {
    private int Id;
    private String FullName;
    public int getId() {
        return Id;
    }

    public void setId(int id) throws InvalidIdException {
        if(id > 0)Id = id;
        else throw new InvalidIdException("You tried to set for a user an invalid ID. Please check the id: "+id);
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) throws InvalidFullNameException {
        if(!fullName.contains(" ")) throw new InvalidFullNameException("The name must contains atleast one space! The given name is: "+fullName);
        else if (fullName.length() < 7) throw new InvalidFullNameException("The given name is too short: "+fullName);
        else if (fullName.length() > 50)throw new InvalidFullNameException("The given name is too long: "+fullName);
        else if (fullName.trim().length() == 0) throw new InvalidFullNameException("The name is full of whitespaces please give a normal one.");
        else FullName = fullName;
    }
}
