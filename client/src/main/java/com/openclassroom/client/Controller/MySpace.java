package com.openclassroom.client.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MySpace {
    @GetMapping("/my_space")
    public String getSpace(){
        return"";
    }
}
