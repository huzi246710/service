package com.wwq.nacos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NacosClient {
    @Autowired
    private  NacosDiscoveryClient nacosDiscoveryClient;

    public List<String> getServices(){
        return nacosDiscoveryClient.getServices();

    }
}
