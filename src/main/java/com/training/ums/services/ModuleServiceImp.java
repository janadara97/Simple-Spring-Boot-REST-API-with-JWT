package com.training.ums.services;
import java.util.ArrayList;
import java.util.List;

import com.training.ums.entity.Lecturers;
import com.training.ums.entity.Modules;

import com.training.ums.repository.LecturersRepository;
import com.training.ums.repository.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImp implements ModuleService {
    
    @Autowired
    private ModuleRepository moduleRepository;



    @Autowired
    private LecturersRepository lecturersRepository;

    @Override
    public List<Modules> getAlModules() {
        List<Modules> modules=new ArrayList<>();
        moduleRepository.findAll().forEach(modules::add);
        return modules;
    }

    @Override
    public Modules addModules(Modules modules) {
        return moduleRepository.save(modules);
    }

    @Override
    public Modules updateModules(int id, Modules modules) {
        Modules exisitingModule=this.moduleRepository.findById(id);
        exisitingModule.setModuleName(modules.getModuleName());
        exisitingModule.setModuleDes(modules.getModuleDes());
        exisitingModule.setModuleCredits(modules.getModuleCredits());
        return this.moduleRepository.save(exisitingModule);
    }

    @Override
    public Modules deletModules(int id) {
        return moduleRepository.deleteById(id);
    }

    @Override
    public Modules addModulesByLectId(Modules modules, int lectId) {
        Lecturers lec=lecturersRepository.findById(lectId);
        modules.setLect(lec);
        return moduleRepository.save(modules);
    }

   

}
