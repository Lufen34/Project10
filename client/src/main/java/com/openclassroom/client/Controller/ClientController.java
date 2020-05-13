package com.openclassroom.client.Controller;

import com.openclassroom.client.Proxy.BookServiceProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ClientController {

    @Autowired
    private BookServiceProxy bookServiceProxy;
    
    @RequestMapping(path="/", method = RequestMethod.GET)    
    public String getHome(Model model){
        
        model.addAttribute("books", bookServiceProxy.getBooks());

        return "index";
    }
}