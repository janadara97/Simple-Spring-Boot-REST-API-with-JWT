package com.training.ums.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.training.ums.entity.Topics;

import org.springframework.stereotype.Component;

@Component
public class TopicsConverter {
    
    public  TopicsDto entityToDto(Topics topics){

        TopicsDto topicsDto=new TopicsDto();

        topicsDto.settId(topics.gettId());
        topicsDto.settName(topics.gettName());
        topicsDto.settDes(topics.gettDes());
        topicsDto.setModuleId(topics.getModules().getModuleId());
        topicsDto.setModuleName(topics.getModules().getModuleName());
        return topicsDto;

    }

    public  List<TopicsDto> entityToDto(List<Topics> topics){

        return topics.stream()
                    .map(x->entityToDto(x)).collect(Collectors.toList());
    }

    public Topics dtoToentity(TopicsDto topicsDto){
        Topics topics=new Topics();

        topics.settId(topicsDto.gettId());
        topics.settName(topicsDto.gettName());
        topics.settDes(topicsDto.gettDes());
        return topics;
    }

    public List<Topics> dtoToentity(List<TopicsDto> topicsDto){
        return topicsDto.stream()
                        .map(x->dtoToentity(x)).collect(Collectors.toList());
    }
}
