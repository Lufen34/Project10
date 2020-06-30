package com.openclassroom.client.Proxy;

import com.openclassroom.client.BookServiceBeans.UserBean;
import com.openclassroom.client.BookServiceBeans.UserBookModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "GATEWAY-SERVER", contextId = "OAUTH-SERVER")
@RibbonClient(name = "OAUTH-SERVER")
@RequestMapping(value = "OAUTH-SERVER")
public interface OAuthServerProxy {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody UserBean userBean);
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity<String> register(@RequestBody UserBean userBean);
    @GetMapping(value = "/account/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBookModel> getAccountByLogin(@PathVariable("login") String login);
}
