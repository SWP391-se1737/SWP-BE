package com.example.miniProject.controller;

import com.example.miniProject.model.Campuses;
import com.example.miniProject.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Campus")

public class CampusController {
    @Autowired
    private CampusService campusService;

    @GetMapping("/listCampus")
    public List<Campuses> campusesList(){
        return campusService.listCampus();
    }

    @PostMapping("/addCampus")
    public String addCampus(@RequestBody Campuses campuses){
        campusService.addCampus(campuses);
        return "Campus is added";
    }

    @PutMapping("/updateCampus/{id}")
    public ResponseEntity<String> updateCampus (@RequestBody Campuses newCampus, @PathVariable int id){
        boolean check = false;
        check = campusService.updateCampus(newCampus,id);
        try {
            if (check){
                return ResponseEntity.ok("Update Success ID:" + id);
            } else {
                return ResponseEntity.ok("ID: " + id + " khong ton tai");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at AccountController:" + e.getMessage());
        }
    }

    @DeleteMapping("/deleteCampus/{id}")
    public ResponseEntity<String> deleteCampus(@PathVariable int id){
        boolean check = false;
        check = campusService.deleteCampus(id);
        try {
            if(check){
                return ResponseEntity.ok("Delete Success Id: " + id);
            }else{
                return ResponseEntity.ok("ID " + id + " khong ton tai");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at AccountController:" + e.getMessage());
        }
    }

    @GetMapping("/getCampusById")
    public Optional<Campuses> getTransactionById(@RequestParam("id") int id) {
        return  campusService.getCampusById(id);
    }
}
