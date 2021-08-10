package com.training.ums.entity;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "module")
public class Modules {

    @Id
    @Column(name = "moduleid")
    private int moduleId;

   @Column(name = "modulename")
    private String moduleName;

   @Column(name = "moduledes")
    private String moduleDes;
    
   @Column(name = "modulecredits")
    private int moduleCredits;

  
    @OneToMany(mappedBy = "modules",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Topics> topics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lid",referencedColumnName = "lid")
    private Lecturers lecturer;

    
    public Modules() {
    }

    public Modules(int moduleId, String moduleName, String moduleDes, int moduleCredits) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDes = moduleDes;
        this.moduleCredits = moduleCredits;
       
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    

    public int getModuleCredits() {
        return moduleCredits;
    }

    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    public String getModuleDes() {
        return moduleDes;
    }

    public void setModuleDes(String moduleDes) {
        this.moduleDes = moduleDes;
    }


    public Set<Topics> getTopics() {
        return topics;
    }

   
   
    public void setTopics(Set<Topics> topics) {
        this.topics = topics;
    }
 
    
   
    public Lecturers getLect() {
        return lecturer;
    }

 
    public void setLect(Lecturers lect) {
        this.lecturer = lect;
    }

    @Override
    public String toString() {
        return "Modules [moduleCredits=" + moduleCredits + ", moduleDes=" + moduleDes + ", moduleId=" + moduleId
                + ", moduleName=" + moduleName + ", topics=" + topics + "]";
    }
    
    
    
}
