package com.training.ums.services;
import java.util.List;
import com.training.ums.entity.Topics;
import org.springframework.stereotype.Service;




@Service
public interface TopicService {
    
    List<Topics>  getTopicsByModule(int id);
    Topics addTopics(Topics topics,int moduleId);
    Topics addTopics(Topics topics);
    Topics updateTopics(int topicId,Topics topics);
    Topics deletTopics(int topicId);
    List<Topics> getAllTopics();
    
}
