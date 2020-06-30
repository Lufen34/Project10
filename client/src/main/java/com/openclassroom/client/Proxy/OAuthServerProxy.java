package com.openclassroom.client.Proxy;

import com.openclassroom.client.BookServiceBeans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "GATEWAY-SERVER", contextId = "OAUTH-SERVER")
@RibbonClient(name = "OAUTH-SERVER")
@RequestMapping(value = "OAUTH-SERVER")
public interface OAuthServerProxy {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody UserBean userBean);
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity<String> register(@RequestBody UserBean userBean);
}
