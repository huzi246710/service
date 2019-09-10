package com.wwq.nacos.controller;


import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.facade.StudentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentFacade studentFacade;

    @PostMapping("/getGirlFriend")
    public GirlFriend getGirl(@RequestBody GirlFriendReq grirl){
        GirlFriend myGirl = studentFacade.getGirlFriend(grirl);
        return myGirl;
    }

}
