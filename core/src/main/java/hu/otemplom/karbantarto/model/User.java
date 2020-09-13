package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.User.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {
    private int Id;
    private String FullName;
    private String Username;

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
        if(fullName == null) throw new InvalidFullNameException("The given fullname is null. Please check again");
        if(!fullName.contains(" ")) throw new InvalidFullNameException("The name must contains atleast one space! The given name is: "+fullName);
        else if (fullName.length() < 7) throw new InvalidFullNameException("The given name is too short(min: 7): "+fullName);
        else if (fullName.length() > 50)throw new InvalidFullNameException("The given name is too long(max:50): "+fullName);
        else if (fullName.trim().length() == 0) throw new InvalidFullNameException("The name is full of whitespaces please give a normal one.");
        else FullName = fullName;
    }
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) throws InvalidUsernameException {
        if(username == null) throw new InvalidUsernameException("The given username is null. Please check again");
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        boolean b = m.find();
        if(username.length() < 4) throw new InvalidUsernameException("The given username is too short(min:4): "+username);
        else if (username.length() > 9) throw new InvalidUsernameException("The given username is too long(max:9): "+username);
        else if (b) throw new InvalidUsernameException("The username cannot contain special chars: "+username);

        else Username = username;
    }


}
