package com.training.ums.controllers;

import java.util.List;

import com.training.ums.dto.LecturerConverter;
import com.training.ums.dto.ModuleConverter;
import com.training.ums.dto.TopicsConverter;
import com.training.ums.entity.Lecturers;
import com.training.ums.entity.Modules;
import com.training.ums.entity.Topics;
import com.training.ums.services.LecturerService;
import com.training.ums.services.ModuleService;
import com.training.ums.services.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    ModuleService moduleService;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    LecturerConverter lecturerConverter;

    @Autowired
    ModuleConverter moduleConverter;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicsConverter topicsConverter;
    
    @GetMapping("/getAllModules")
    @Secured({"ROLE_STUDENT"})
    public ResponseEntity<?> gettAllModules(){
        List<Modules>list=moduleService.getAlModules();

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(moduleConverter.entityToDto(list));
        }
    }
    @GetMapping("/getAllLect")
    @Secured({"ROLE_STUDENT"})
    public ResponseEntity <?> getAllLecturer()
    {
    List<Lecturers>list=lecturerService.getAlLecturers();

    if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(lecturerConverter.entityToDto(list));
            
        }
    }

    @RequestMapping("/getAllTopics")
    @Secured({"ROLE_STUDENT"})
    public ResponseEntity <?> getAllTopics() 
    {
    List<Topics>list=topicService.getAllTopics();

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(topicsConverter.entityToDto(list));
        }
 
    }

    @RequestMapping("/getTopics/{moduleId}") 
    @Secured({"ROLE_ADMIN","ROLE_LECTURER","ROLE_STUDENT"})
    public ResponseEntity <?> getAllTopics(@PathVariable int moduleId) throws Exception
    {
    List<Topics>list=topicService.getTopicsByModule(moduleId);

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(topicsConverter.entityToDto(list));
            
        }
    }
}
