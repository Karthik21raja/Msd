package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/food")
    public String getFood() {
    	
        String customerservicelink = "http://localhost:8080/customers";
        String customers = restTemplate.getForObject( customerservicelink, String.class);
        
        return "Food: Briyani,Dosai,Parotta; " + customers;
    }
}
