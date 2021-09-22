package com.training.ums.controllers;
import java.util.List;

import com.training.ums.apiResponse;
import com.training.ums.dto.LecturerConverter;
import com.training.ums.dto.LecturerDto;
import com.training.ums.entity.Lecturers;
import com.training.ums.services.LecturerService;
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
@RequestMapping("/lecturers")
public class LecturersController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LecturerConverter converter;

    
    @RequestMapping("/getAllLect")
   // @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity <?> getAllLecturer()
    {
    List<Lecturers>list=lecturerService.getAlLecturers();

    if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false,"Lecture List is Empty",null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, "Data Fetch is Completed", converter.entityToDto(list)) );
            
        }

       
    }

    @PostMapping("/addLect")
    //@Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> addLecturer(@RequestBody LecturerDto lecturerDto)
    
    {

        Lecturers lect=null;
        try 
    {   
       lect=converter.dtoToEntity(lecturerDto);
       lect=lecturerService.addLecturers(lect);
       return ResponseEntity.ok(new apiResponse(true, "Lecture Added Successfully", converter.entityToDto(lect)) );
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
        return ResponseEntity.ok().body(new apiResponse(false,"Unsuccessfull", null));
    }
       
    }

    @PutMapping("/updateLect/{id}")
    //@Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> updateLecturer(@PathVariable int id,@RequestBody LecturerDto lecturerDto)
    {
        Lecturers lect=null;
        try 
        {   
            lect=converter.dtoToEntity(lecturerDto);
            lect=lecturerService.updateLecturers(id, lect);
            return ResponseEntity.ok().body(new apiResponse(true, "Lecturer Updated Successfully", converter.entityToDto(lect)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessfull", null));
        }

        
            
    }

    @DeleteMapping("deleteLect/{id}")
   // @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> deleteLecturer(@PathVariable int id)
    {

        
        try 
        {   
           return ResponseEntity.ok().body(new apiResponse(true, "Lecture Deleted Successfully", lecturerService.deletLecturers(id)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false,"Unsuccessful",null));
        }


       
            
    }

        
    }


