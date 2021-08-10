package com.training.ums.services;
import java.util.ArrayList;
import java.util.List;
import com.training.ums.entity.Modules;
import com.training.ums.entity.Topics;
import com.training.ums.exception.ResourceNotFoundException;
import com.training.ums.repository.ModuleRepository;
import com.training.ums.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TopicServiceImp implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Topics> getTopicsByModule(int id) {
        List<Topics> topics=new ArrayList<>();
        topicRepository.findAllByModulesModuleId(id).forEach(topics::add);
        System.out.println(topics);
        return topics;
        
    }

    @Override
    public Topics addTopics(Topics topics,int moduleId) {
        
        Modules mod=moduleRepository.findById(moduleId);
        topics.setModules(mod);
        return topicRepository.save(topics);




        
    }

    @Override
    public Topics updateTopics(int topicId, Topics topics) {
        Topics exixtingTopic=this.topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic Not Found with - "+topicId));
        exixtingTopic.settName(topics.gettName());
        exixtingTopic.settDes(topics.gettDes());
        return topicRepository.save(exixtingTopic);

    }

    @Override
    public Topics deletTopics(int topicId) {
        return topicRepository.deleteById(topicId);
    }

    @Override
    public List<Topics> getAllTopics() {
        List<Topics> topics=new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    @Override
    public Topics addTopics(Topics topics) {
        return topicRepository.save(topics);
    }
    
}
