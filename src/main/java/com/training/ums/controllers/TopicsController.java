package com.training.ums.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.training.ums.apiResponse;
import com.training.ums.dto.TopicsConverter;
import com.training.ums.dto.TopicsDto;
import com.training.ums.entity.Topics;
import com.training.ums.services.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080/")
// @RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicsConverter converter;

    private String dnf = "Data Not Found";
    private String fds = "Data Fetched Successfully";

    @RequestMapping("/getTopics/{moduleId}") 
    public ResponseEntity <?> getAllTopics(@PathVariable int moduleId) {
    List<Topics>list=topicService.getTopicsByModule(moduleId);

        if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, dnf, null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, fds, converter.entityToDto(list)));
            
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
            return ResponseEntity.ok().body(new apiResponse(true, fds, converter.entityToDto(list)));
        }
 
    }

    @PostMapping("/addTopic/{moduleId}")
    public ResponseEntity<?> addTopic(@RequestBody TopicsDto topicDto, @PathVariable int moduleId) {
        Topics topic = null;
        try {
            topic = converter.dtoToentity(topicDto);
            topic = topicService.addTopics(topic, moduleId);
            return ResponseEntity.ok().body(new apiResponse(true, "Topic Added Successfully", converter.entityToDto(topic)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }

    }

    @PutMapping("/updateTopics/{topicId}")
    public ResponseEntity<?> updatetopicurer(@PathVariable int topicId, @RequestBody TopicsDto topicDto) {
        Topics topic = null;
        try {
            topic = converter.dtoToentity(topicDto);
            topic = topicService.updateTopics(topicId, topic);
            return ResponseEntity.ok().body(new apiResponse(true, "Module updated successfully", converter.entityToDto(topic)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
    }

    @DeleteMapping("deleteTopic/{topicId}")
    public ResponseEntity<?> deletetopicurer(@PathVariable int topicId) {

        try {

            return ResponseEntity.ok().body(new apiResponse(true, "Successfully Deleted", topicService.deletTopics(topicId)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }

    }
}
