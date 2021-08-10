package com.training.ums.repository;



import java.util.List;
import com.training.ums.entity.Topics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topics,Integer> {
    Topics deleteById(int id);
    List<Topics> findAllByModulesModuleId(int id);
    
    //List<Topics> getByModulesModuleId(int id);
    Topics save(List<Topics> topics);
    
    
}
