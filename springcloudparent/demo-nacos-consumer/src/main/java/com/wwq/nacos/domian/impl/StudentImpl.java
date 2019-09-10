package com.wwq.nacos.domian.impl;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.facade.GirlFriendFacade;
import com.wwq.nacos.facade.StudentFacade;
import com.wwq.nacos.rpc.feign.NacosFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentImpl implements StudentFacade {

    @Autowired
    private NacosFeignClient nacosFeignClient;
    @Override
    public GirlFriend getGirlFriend(GirlFriendReq grirl) {
        return nacosFeignClient.getGirl(grirl);
    }
}
