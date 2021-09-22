package com.training.ums.entity;

public class RoleToPrivilege {
    private String roleName;
    private String privilegeName;

    
    public RoleToPrivilege() {
    }
    public RoleToPrivilege(String roleName, String privilegeName) {
        this.roleName = roleName;
        this.privilegeName = privilegeName;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getPrivilegeName() {
        return privilegeName;
    }
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }
    @Override
    public String toString() {
        return "RoleToPrivilege [privilegeName=" + privilegeName + ", roleName=" + roleName + "]";
    }

    
}
