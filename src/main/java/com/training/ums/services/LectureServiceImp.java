package com.training.ums.services;
import java.util.ArrayList;
import java.util.List;
import com.training.ums.entity.Lecturers;
import com.training.ums.exception.custom.exception.BusinessException;
import com.training.ums.exception.custom.exception.EmptyListException;
import com.training.ums.repository.LecturersRepository;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public  class LectureServiceImp implements LecturerService{

    @Autowired
    private LecturersRepository lecturersRepository;

  
 
    @Override
    public List<Lecturers> getAlLecturers() {
        List<Lecturers>lecturers=new ArrayList<>();
        lecturersRepository.findAll().forEach(lecturers::add);
        
            if(lecturers.isEmpty()){
               throw new EmptyListException("601", "The System Does Not Have Any Lecturers List Now");
            }
            return lecturers;
            
        }
        
    

    @Override
    public Lecturers addLecturers(Lecturers lecturers) {
        return lecturersRepository.save(lecturers);
            
    }

    @Override
    public Lecturers updateLecturers(int id, Lecturers lecturers) {
        
        Lecturers exisitingLecturer=this.lecturersRepository.findById(id);
        exisitingLecturer.setlName(lecturers.getlName());
        exisitingLecturer.setlNumber(lecturers.getlNumber());
        return this.lecturersRepository.save(exisitingLecturer);
    }

    @Override
    public Lecturers deletLecturers(int id) {
        try {
            return lecturersRepository.deleteById(id);
        } catch (BusinessException e) {
            throw new BusinessException("605","Delete Is Not Completed.Please Check");
        }
        
    }



    

    
}
