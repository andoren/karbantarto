package hu.otemplom.karbantarto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidBossException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.Area.InvalidNameException;

import javax.persistence.*;

@Entity()
@Table(name = "area")
public class Area {
    public Area() {

    }

    public Area(int id, String name, User boss) {
        this.id = id;
        this.name = name;
        this.boss = boss;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="name", nullable = false)
    private String name;
    @ManyToOne( )
    @JoinColumn(name = "boss", nullable = true)
    @JsonIgnoreProperties("areas")
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
       this.boss = boss;
    }
}
