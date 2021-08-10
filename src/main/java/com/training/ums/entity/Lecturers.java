package com.training.ums.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "lecturers")
public class Lecturers {

    @Id
    @Column(name = "lid")
    private int lId;
    
    @Column(name = "lname")
    private String lName;
    
    @Column(name = "lnumber")
    private int lNumber;

    @OneToMany(mappedBy = "lecturer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Modules> modules;
    

    
    public Lecturers(int lId, String lName, int lNumber) {
        this.lId = lId;
        this.lName = lName;
        this.lNumber = lNumber;
    }
    public Lecturers() {
       
    }


    public int getlId() {
        return lId;
    }
    public void setlId(int lId) {
        this.lId = lId;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public int getlNumber() {
        return lNumber;
    }
    public void setlNumber(int lNumber) {
        this.lNumber = lNumber;
    }

  
    public Set<Modules> getModules() {
        return modules;
    }
   
    public void setModules(Set<Modules> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Lecturers [lId=" + lId + ", lName=" + lName + ", lNumber=" + lNumber + ", modules=" + modules + "]";
    }
    
    
}
