package com.training.ums.dto;

import java.util.Set;

public class ModuleDto {

    private int moduleId;
    private String moduleName;
    private String moduleDes;
    private int moduleCredits;
    
    private Set<String> topicNames;
    
    public ModuleDto() {
        
    }
    
    public ModuleDto(int moduleId, String moduleName, String moduleDes, int moduleCredits) {
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
    public String getModuleDes() {
        return moduleDes;
    }
    public void setModuleDes(String moduleDes) {
        this.moduleDes = moduleDes;
    }
    public int getModuleCredits() {
        return moduleCredits;
    }
    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    public Set<String> getTopics() {
        return topicNames;
    }

    public void setTopics(Set<String> topicNames) {
        this.topicNames = topicNames;
    }

    
   

    

    
    
}
