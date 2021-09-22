package com.training.ums.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Privilege {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(int id, String privilegeName, Collection<Role> roles) {
        this.id = id;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }
    public Privilege(final String name) {
        super();
        this.privilegeName = name;
    }
    public Privilege() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    

}
