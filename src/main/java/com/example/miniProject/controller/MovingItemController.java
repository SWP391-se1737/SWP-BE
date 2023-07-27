package com.example.miniProject.controller;

import com.example.miniProject.model.MovingItems;
import com.example.miniProject.service.MovingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MovingItems")
public class MovingItemController {
    @Autowired
    private MovingItemService movingItemService;

    @GetMapping("listItems")
    public List<MovingItems> itemsList(){
        return movingItemService.listItem();
    }
    @PostMapping("/addItems")
    public String addItem(@RequestBody MovingItems movingItems){
        movingItemService.addItem(movingItems);
        return "Items is added";
    }

    @DeleteMapping("/deleteItems/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id){
        boolean check = false;
        check = movingItemService.deleteItem(id);
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
    @GetMapping("/findItemById/{id}")
    public List<MovingItems> findItemById(@PathVariable int id){
        return movingItemService.findItemById(id);
    }
}
