package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.InvalidIdException;

public class User {
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) throws InvalidIdException {
        if(id > 0)Id = id;
        else throw new InvalidIdException("You tried to set for a user an invalid ID. Please check the id: "+id);
    }
}
