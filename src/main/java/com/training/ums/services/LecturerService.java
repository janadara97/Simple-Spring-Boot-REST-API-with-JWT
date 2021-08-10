package com.training.ums.services;
import java.util.List;
import com.training.ums.entity.Lecturers;

import org.springframework.stereotype.Service;


@Service
public interface LecturerService {
    
    List<Lecturers> getAlLecturers();
    Lecturers addLecturers(Lecturers lecturers);
    Lecturers updateLecturers(int id,Lecturers lecturers);
    Lecturers deletLecturers(int id);
    
}
