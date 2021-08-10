package com.training.ums.dto;

public class TopicsDto {
    
    private int tId;
    private String tName;
    private String tDes;
    private int moduleId;
    private String moduleName;

    
    public TopicsDto() {
       
    }

    public TopicsDto(int tId, String tName, String tDes) {
        this.tId = tId;
        this.tName = tName;
        this.tDes = tDes;
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

    


}
