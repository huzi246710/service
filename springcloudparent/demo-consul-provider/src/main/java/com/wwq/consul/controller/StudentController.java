package com.wwq.consul.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/getGirlFriend")
    public String getGirl(){

        return "成功";
    }
    @GetMapping("/actuator/health")
    public String health(){

        return "success";
    }
}
