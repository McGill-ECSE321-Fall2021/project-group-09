package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Id;

public class Member extends Account{
private String name;
	
    public void setName(String value) {
        this.name = value;
    }
    
    @Id
    public String getName() {
        return this.name;
    }
}
