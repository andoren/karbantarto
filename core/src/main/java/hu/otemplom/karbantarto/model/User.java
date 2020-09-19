package hu.otemplom.karbantarto.model;

import hu.otemplom.karbantarto.model.Exceptions.User.*;
import hu.otemplom.karbantarto.model.Role;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {


    public User(){

    }
    public User(int id, String fullname, String username, Role role) throws InvalidRoleException, InvalidUsernameException, InvalidFullNameException, InvalidIdException {
        setId(id);
        setFullName(fullname);
        setUsername(username);
        setRole(role);
    }
    public User(int id, String fullName, String username, Role role, String password) throws InvalidUsernameException, InvalidIdException, InvalidFullNameException, InvalidRoleException, InvalidPasswordException {
        this(id,fullName,username,role);
        setPassword(password);
    }

    private int Id;
    private String FullName;
    private String Username;
    private Role Role;
    private String Password;

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
        else if (username.length() > 15) throw new InvalidUsernameException("The given username is too long(max:15): "+username);
        else if (b) throw new InvalidUsernameException("The username cannot contain special chars: "+username);

        else Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if(password == null) throw new InvalidPasswordException("The password cannot be null.");
        else if (password.length() < 6) throw new InvalidPasswordException("The password is too short(min:6)");
        else if (password.length() > 12) throw new InvalidPasswordException("The password is too short(max:12)");
        else if (password.trim().length() == 0) throw new InvalidPasswordException("The password cannot be full of white spaces");
        Password = password;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role role) throws InvalidRoleException {
        if(role == null) throw new InvalidRoleException("The given role is null. Please check again");
        Role = role;
    }
}
