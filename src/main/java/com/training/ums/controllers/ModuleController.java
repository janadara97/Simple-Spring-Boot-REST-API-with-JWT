package com.training.ums.controllers;
import java.util.List;

import com.training.ums.apiResponse;
import com.training.ums.dto.ModuleConverter;
import com.training.ums.dto.ModuleDto;
import com.training.ums.entity.Modules;
import com.training.ums.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("http://localhost:8080/")
//@RequestMapping("/modules")
public class ModuleController {


    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleConverter moduleConverter;
    
    @RequestMapping("/getModules")
   
    public ResponseEntity <?> getAllModules()
    {
    List<Modules>list=moduleService.getAlModules();

        if(list.isEmpty())
        {
            return ResponseEntity.ok().body(new apiResponse(false, "List is Empty", null));
        }
        else
        {
            return ResponseEntity.ok().body(new apiResponse(true, "Data Fetched Successfully", moduleConverter.entityToDto(list)));
        }

        
    }

    @PostMapping("/addModules")
   // @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> addModules(@RequestBody ModuleDto moduleDto)
    {
        Modules module=null;
        try 
        {
         
            module=moduleConverter.dtoToEntity(moduleDto);
           
            module=moduleService.addModules(module);
          
            return ResponseEntity.ok(new apiResponse(true, "Module added successfully", moduleConverter.entityToDto(module)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
        
        
        }

        @PutMapping("/updateModule/{id}")
     
        public ResponseEntity<?> updatetopicurer(@PathVariable int id,@RequestBody ModuleDto moduleDto)
        {
            Modules module=null;
            try 
            {
               module=moduleConverter.dtoToEntity(moduleDto);
               module=moduleService.updateModules(id, module);
               return ResponseEntity.ok(new apiResponse(true, "Module updated successfully", moduleConverter.entityToDto(module)));
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return ResponseEntity.ok().body(new apiResponse(false,"Unsuccessful", null));
            }
        }

        @DeleteMapping("deleteModule/{id}")
       
        public ResponseEntity<?> deletetopicurer(@PathVariable int id)
        {
            Modules module=null;
            try 
            {
                module=moduleService.deletModules(id);
                return ResponseEntity.ok().body(new apiResponse(true, "Successfully Deleted", null));
            } 
            catch (Exception e) 
            {
                return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
            }
           
        }

        @PostMapping(
        value = "/addModules/{lectId}")
        
        public ResponseEntity <?> addTopic(@RequestBody ModuleDto moduleDto,@PathVariable int lectId)
        {
            Modules module=null;
        try 
        {   module=moduleConverter.dtoToEntity(moduleDto);
            module=moduleService.addModulesByLectId(module, lectId);
            return ResponseEntity.ok().body(new apiResponse(true, "Module successfully added", moduleConverter.entityToDto(module)));
        }    
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.ok().body(new apiResponse(false, "Unsuccessful", null));
        }
           
        }
    
}
