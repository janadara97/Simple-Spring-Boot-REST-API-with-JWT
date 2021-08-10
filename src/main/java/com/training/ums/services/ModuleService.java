package com.training.ums.services;

import java.util.List;

import com.training.ums.entity.Modules;


public interface ModuleService  {
    
    List<Modules> getAlModules();
    Modules addModulesByLectId(Modules modules,int lectId);
    Modules addModules(Modules modules);
    Modules updateModules(int id,Modules modules);
    Modules deletModules(int id);
    

}
