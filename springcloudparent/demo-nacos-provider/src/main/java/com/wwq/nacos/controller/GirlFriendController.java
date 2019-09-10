package com.wwq.nacos.controller;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.facade.GirlFriendFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GirlFriendController {
    @Autowired
    private GirlFriendFacade girlFriendFacade;

    @PostMapping("/getGirlFriend")
    public GirlFriend getGirl(@RequestBody GirlFriendReq grirl){
        GirlFriend myGirl = girlFriendFacade.getGirlFriend(grirl);
        return myGirl;
    }

}
