package com.training.ums.repository;

import com.training.ums.entity.Lecturers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturersRepository extends JpaRepository<Lecturers,Integer> {

    Lecturers deleteById(int Id);
    Lecturers findById(int id);
    
}
