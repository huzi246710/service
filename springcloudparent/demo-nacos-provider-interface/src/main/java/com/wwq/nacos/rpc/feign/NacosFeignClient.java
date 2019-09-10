package com.wwq.nacos.rpc.feign;

import com.wwq.nacos.rpc.interfaces.GirlFriendRpcFacade;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demo-nacos-provider")
public interface NacosFeignClient extends GirlFriendRpcFacade{
}
