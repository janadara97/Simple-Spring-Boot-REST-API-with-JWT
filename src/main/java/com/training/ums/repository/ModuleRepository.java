package com.training.ums.repository;
import com.training.ums.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository <Modules,Integer> {
    Modules deleteById(int id);
    Modules findById(int id);
    
    
   
    //Topics addTopicsbyModulesModuleId(Topics topics,int id);
}
