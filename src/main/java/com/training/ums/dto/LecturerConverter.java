package com.training.ums.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.training.ums.entity.Lecturers;

import org.springframework.stereotype.Component;

@Component
public class LecturerConverter {
    
    
    public LecturerDto entityToDto(Lecturers lecturer){
        LecturerDto lecturerDto = new LecturerDto();
        lecturerDto.setlId(lecturer.getlId());
        lecturerDto.setlName(lecturer.getlName());
        lecturerDto.setlNumber(lecturer.getlNumber());
        if(lecturer.getModules()==null){
            return lecturerDto;
        }
        else{
            Set <String> moduleNames=lecturer.getModules().stream()
                                            .map((x)->x.getModuleName()).collect(Collectors.toSet());
        lecturerDto.setModuleNames(moduleNames);
        return lecturerDto;
        }
        
    }

    public List<LecturerDto> entityToDto(List<Lecturers> lecturer){
        return lecturer.stream().map((x)->entityToDto(x)).collect(Collectors.toList());
    }

    public Lecturers dtoToEntity(LecturerDto lecturerDto){
        Lecturers lect=new Lecturers();
        lect.setlId(lecturerDto.getlId());
        lect.setlName(lecturerDto.getlName());
        lect.setlNumber(lecturerDto.getlNumber());
        return lect;
    }

    public List<Lecturers> dtoToEntity(List<LecturerDto> lecturerDto){
       return lecturerDto.stream().map((x)->dtoToEntity(x)).collect(Collectors.toList());
    }


    
    
}
