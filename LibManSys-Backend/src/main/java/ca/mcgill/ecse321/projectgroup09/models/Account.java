package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Account {
	
	private String name;
	
    public void setName(String value) {
        this.name = value;
    }
    
    @Id
    public String getName() {
        return this.name;
    }
}
