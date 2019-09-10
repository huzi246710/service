package com.wwq.nacos.facade;


import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;

public interface StudentFacade {
    GirlFriend getGirlFriend(GirlFriendReq grirl);
}
