package com.wwq.nacos.rpc.interfaces;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface GirlFriendRpcFacade {
    @RequestMapping(value = "/getGirl", method = RequestMethod.POST)
    GirlFriend getGirl(@RequestBody GirlFriendReq grirl);
}
