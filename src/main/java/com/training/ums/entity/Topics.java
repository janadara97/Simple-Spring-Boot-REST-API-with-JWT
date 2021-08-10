package com.training.ums.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "topic")
public class Topics implements Serializable {

   @Id
   @Column(name = "tid")
    private int tId;

   @Column(name = "tname")
    private String tName;

   @Column(name = "tdes")
    private String tDes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid",referencedColumnName = "moduleid")
    private Modules modules;


    public Topics() {
    }

    public Topics(int tId, String tName, String tDes,int moduleId,Modules modules) {
        super();
        this.tId = tId;
        this.tName = tName;
        this.tDes = tDes;
        this.modules=modules;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDes() {
        return tDes;
    }

    public void settDes(String tDes) {
        this.tDes = tDes;
    }

    @Override
    public String toString() {
        return "Topics [modules=" + modules + ", tDes=" + tDes + ", tId=" + tId + ", tName=" + tName + "]";
    }

    
    public Modules getModules() {
        return modules;
    }

   
    public void setModules(Modules modules) {
        this.modules = modules;
    }
    
    
}
