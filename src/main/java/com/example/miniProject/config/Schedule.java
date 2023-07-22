package com.example.miniProject.config;

import com.example.miniProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    @Autowired
    private ProductService productService;
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleFixedDelayTask() {

        productService.autoUpdateProductStatus();
        System.out.println("Scan product status success");
    }
}
