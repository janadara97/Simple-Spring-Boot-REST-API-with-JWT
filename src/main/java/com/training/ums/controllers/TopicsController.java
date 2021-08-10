package com.training.ums.controllers;

import java.util.List;

import com.training.ums.dto.TopicsConverter;
import com.training.ums.dto.TopicsDto;
import com.training.ums.entity.Topics;
import com.training.ums.services.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicsConverter converter;
    

    @RequestMapping("/getTopics/{moduleId}") 
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity <?> getAllTopics(@PathVariable int moduleId) throws Exception
    {
    List<Topics>list=topicService.getTopicsByModule(moduleId);

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(converter.entityToDto(list));
            
        }
    }


    @RequestMapping("/getAllTopics")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity <?> getAllTopics() 
    {
    List<Topics>list=topicService.getAllTopics();

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(converter.entityToDto(list));
        }
 
    }

    @PostMapping(
        value = "/addTopic/{moduleId}")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity <?> addTopic(@RequestBody TopicsDto topicDto,@PathVariable int moduleId)
    {
        Topics topic=null;
    try 
    {   topic=converter.dtoToentity(topicDto);
        topic=topicService.addTopics(topic, moduleId);
        return ResponseEntity.ok().body(converter.entityToDto(topic));
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
           
    }


    @PutMapping("/updateTopics/{topicId}")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> updatetopicurer(@PathVariable int topicId,@RequestBody TopicsDto topicDto)
    {
        Topics topic=null;
        try 
        {
            topic=converter.dtoToentity(topicDto);
            topic=topicService.updateTopics(topicId, topic);
            return ResponseEntity.ok(converter.entityToDto(topic));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("deleteTopic/{topicId}")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> deletetopicurer(@PathVariable int topicId)
    {
       
        try 
        {
            
            return ResponseEntity.ok(topicService.deletTopics(topicId));
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
           
    }
}
