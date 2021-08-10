package com.training.ums.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.training.ums.entity.Modules;

import org.springframework.stereotype.Component;

@Component
public class ModuleConverter {

    public ModuleDto entityToDto(Modules modules) {

        ModuleDto moduleDto = new ModuleDto();

        moduleDto.setModuleId(modules.getModuleId());
        moduleDto.setModuleName(modules.getModuleName());
        moduleDto.setModuleDes(modules.getModuleDes());
        moduleDto.setModuleCredits(modules.getModuleCredits());

        if (modules.getTopics() == null) {
            return moduleDto;
        }
        Set<String> topicNames = modules.getTopics().stream().map((x) -> x.gettName()).collect(Collectors.toSet());
        moduleDto.setTopics(topicNames);
        return moduleDto;

    }

    public List<ModuleDto> entityToDto(List<Modules> modules) {

        return modules.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Modules dtoToEntity(ModuleDto moduleDto) {
        Modules modules = new Modules();

        modules.setModuleId(moduleDto.getModuleId());
        modules.setModuleName(moduleDto.getModuleName());
        modules.setModuleDes(moduleDto.getModuleDes());
        modules.setModuleCredits(modules.getModuleCredits());
        return modules;
    }

    public List<Modules> dtoToEntity(List<ModuleDto> moduleDto) {
        return moduleDto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
