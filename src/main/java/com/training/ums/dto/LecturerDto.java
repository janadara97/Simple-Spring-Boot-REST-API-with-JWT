package com.training.ums.dto;

import java.util.Set;




public class LecturerDto {
    
    private int lId;
    private String lName;
    private int lNumber;
    private Set<String> moduleNames;


    public LecturerDto() {
       
    }

    public LecturerDto(int lId, String lName, int lNumber) {
        this.lId = lId;
        this.lName = lName;
        this.lNumber = lNumber;
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

    public Set<String> getModuleNames() {
        return moduleNames;
    }

    public void setModuleNames(Set<String> moduleNames) {
        this.moduleNames = moduleNames;
    }

    @Override
    public String toString() {
        return "LecturerDto [lId=" + lId + ", lName=" + lName + ", lNumber=" + lNumber + ", moduleNames=" + moduleNames
                + "]";
    }

    
    
}
