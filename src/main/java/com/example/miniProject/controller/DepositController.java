package com.example.miniProject.controller;

import com.example.miniProject.model.Deposits;
import com.example.miniProject.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Deposits")
public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping("listDeposits")
    public List<Deposits> depositsList(){
        return depositService.listDeposit();
    }
    @PostMapping("/addDeposits")
    public String addDeposit(@RequestBody Deposits deposits){
        depositService.addDeposit(deposits);
        return "Deposit is added";
    }

    @PutMapping("/updateDeposit/{id}")
    public ResponseEntity<String> updateDeposit(@RequestBody Deposits newDeposit, @PathVariable int id){
        boolean check = false;
        check = depositService.updateDeposit(newDeposit,id);
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

    @DeleteMapping("/deleteDeposit/{id}")
    public ResponseEntity<String> deleteDeposit(@PathVariable int id){
        boolean check = false;
        check = depositService.deleteDeposit(id);
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
}
