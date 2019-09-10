package com.wwq.nacos.rpc.impl;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.rpc.interfaces.GirlFriendRpcFacade;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GirlFriendRpcImpl implements GirlFriendRpcFacade {
    @Override
    public GirlFriend getGirl(@RequestBody GirlFriendReq grirl) {
        GirlFriend girlFriend = new GirlFriend();
        girlFriend.setAge(grirl.getAge());
        if(grirl.getAge().equals("24")){
            girlFriend.setName("王文清");
            girlFriend.setHeight("175");
        }if(grirl.getAge().equals("25")){
            girlFriend.setName("温志右");
            girlFriend.setHeight("176");
        }if(grirl.getAge().equals("26")){
            girlFriend.setName("王锋");
            girlFriend.setHeight("177");
        }
        return girlFriend;
    }
}
