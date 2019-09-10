package com.wwq.nacos.domain.impl;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.facade.GirlFriendFacade;
import org.springframework.stereotype.Service;

@Service
public class GirlFriendImpl implements GirlFriendFacade {
    @Override
    public GirlFriend getGirlFriend(GirlFriendReq grirl) {
        GirlFriend girlFriend = new GirlFriend();
        girlFriend.setAge(grirl.getAge());
        if(grirl.getAge().equals("21")){
            girlFriend.setName("王文清");
            girlFriend.setHeight("175");
        }if(grirl.getAge().equals("22")){
            girlFriend.setName("温志右");
            girlFriend.setHeight("176");
        }if(grirl.getAge().equals("23")){
            girlFriend.setName("王锋");
            girlFriend.setHeight("177");
        }
        return girlFriend;
    }
}
