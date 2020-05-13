package com.openclassroom.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ClientController {
    
    @RequestMapping(path="/", method = RequestMethod.GET)    
    public String getHome(Model model){
        
        return "index";
    }
}