package com.training.ums.controllers;
import java.util.List;

import com.training.ums.dto.ModuleConverter;
import com.training.ums.dto.ModuleDto;
import com.training.ums.entity.Modules;
import com.training.ums.services.ModuleService;
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
@RequestMapping("/modules")
public class ModuleController {


    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleConverter moduleConverter;
    
    @RequestMapping("/getModules")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity <?> getAllModules()
    {
    List<Modules>list=moduleService.getAlModules();

        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(moduleConverter.entityToDto(list));
        }

        
    }

    @PostMapping("/addModules")
    @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
    public ResponseEntity<?> addModules(@RequestBody ModuleDto moduleDto)
    {
        Modules module=null;
        try 
        {
            System.out.println(module);
            System.out.println(moduleDto);
            module=moduleConverter.dtoToEntity(moduleDto);
            System.out.println(module);
            module=moduleService.addModules(module);
            System.out.println(module);
            return ResponseEntity.ok(moduleConverter.entityToDto(module));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        
        }

        @PutMapping("/updateModule/{id}")
        @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
        public ResponseEntity<?> updatetopicurer(@PathVariable int id,@RequestBody ModuleDto moduleDto)
        {
            Modules module=null;
            try 
            {
               module=moduleConverter.dtoToEntity(moduleDto);
               module=moduleService.updateModules(id, module);
               return ResponseEntity.ok(moduleConverter.entityToDto(module));
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @DeleteMapping("deleteModule/{id}")
        @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
        public ResponseEntity<?> deletetopicurer(@PathVariable int id)
        {
            Modules module=null;
            try 
            {
                module=moduleService.deletModules(id);
                return ResponseEntity.ok().body(module);
            } 
            catch (Exception e) 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
           
        }

        @PostMapping(
        value = "/addModules/{lectId}")
        @Secured({"ROLE_ADMIN","ROLE_LECTURER"})
        public ResponseEntity <?> addTopic(@RequestBody ModuleDto moduleDto,@PathVariable int lectId)
        {
            Modules module=null;
        try 
        {   module=moduleConverter.dtoToEntity(moduleDto);
            module=moduleService.addModulesByLectId(module, lectId);
            return ResponseEntity.ok(moduleConverter.entityToDto(module));
        }    
        catch (Exception e) 
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
           
        }
    
}
