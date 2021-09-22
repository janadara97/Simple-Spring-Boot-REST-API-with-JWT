package com.training.ums.controllers;

import java.util.List;

import com.training.ums.apiResponse;
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

    private String dnf="Data Not Found";
    private String fds="Data Fetched Successfully";
    
    @GetMapping("/getAllModules")
   
    public ResponseEntity<?> gettAllModules(){
        List<Modules>list=moduleService.getAlModules();

        if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, fds, moduleConverter.entityToDto(list)));
        }
    }
    @GetMapping("/getAllLect")
   
    public ResponseEntity <?> getAllLecturer()
    {
    List<Lecturers>list=lecturerService.getAlLecturers();

    if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, fds, lecturerConverter.entityToDto(list)));
            
        }
    }

    @RequestMapping("/getAllTopics")
    
    public ResponseEntity <?> getAllTopics() 
    {
    List<Topics>list=topicService.getAllTopics();

        if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, fds, topicsConverter.entityToDto(list)));
        }
 
    }

    @RequestMapping("/getTopics/{moduleId}") 
   
    public ResponseEntity <?> getAllTopics(@PathVariable int moduleId) 
    {
    List<Topics>list=topicService.getTopicsByModule(moduleId);

        if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true,fds,topicsConverter.entityToDto(list)));
            
        }
    }
}
