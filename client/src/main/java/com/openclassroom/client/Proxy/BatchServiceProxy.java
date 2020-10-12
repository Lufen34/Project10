package com.openclassroom.client.Proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "GATEWAY-SERVER", contextId = "BATCH-SERVICE")
@RibbonClient(name = "BATCH-SERVICE")
@RequestMapping(value = "BATCH-SERVICE/")
public interface BatchServiceProxy {
    @PostMapping("sendAcceptMail/")
    void sendAcceptMail(@RequestBody String email);
}
