package com.wwq.nacos.controller;

import com.wwq.nacos.domain.pojo.GirlFriend;
import com.wwq.nacos.domain.req.GirlFriendReq;
import com.wwq.nacos.domain.service.NacosClient;
import com.wwq.nacos.facade.GirlFriendFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryClient;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServerList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GirlFriendController {
    @Autowired
    private GirlFriendFacade girlFriendFacade;
    @Value("${useLocalCache:false}")
    private String useLocalCache;
    @Autowired
    private NacosClient nacosClient;

    @PostMapping("/getGirlFriend")
    public List<String> getGirl(@RequestBody GirlFriendReq grirl){
        GirlFriend myGirl = girlFriendFacade.getGirlFriend(grirl);
        //NacosServerList nacosServerList = new NacosServerList("demo-nacos-provider");
        //List<NacosServer> list=  nacosServerList.getInitialListOfServers();
       // NacosDiscoveryClient nacosDiscoveryClient = new NacosDiscoveryClient();
        List<String > list = nacosClient.getServices();
        return list;
    }
    @GetMapping("/test")
    public String test(){
        return useLocalCache;
    }

    public String getUseLocalCache() {
        return useLocalCache;
    }


}
