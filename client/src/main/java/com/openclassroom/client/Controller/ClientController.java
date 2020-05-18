package com.openclassroom.client.Controller;

import com.openclassroom.client.Proxy.BookServiceProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ClientController {

    @Autowired
    private BookServiceProxy bookServiceProxy;

    private boolean isFilteredByAuthor;
    private boolean isFilteredByTitle;
    private boolean isFilteredByIsbn;

    private void setFilterState(String byAuthor, String byTitle, String byIsbn) {
    	if(byAuthor.contains("on"))
            isFilteredByAuthor = true;
        else if(byTitle.contains("on"))
            isFilteredByTitle = true;
        else if(byIsbn.contains("on"))
            isFilteredByIsbn = true;
    }

    private void resetFilterState() {
        isFilteredByAuthor = false;
        isFilteredByTitle = false;
        isFilteredByIsbn = false;
    }
    
    @RequestMapping(path="/", method = RequestMethod.GET)    
    public String getHome(Model model){
        return "index";
    }

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String getBooks(Model model, @RequestParam(defaultValue = "")String param, @RequestParam(defaultValue = "")String authors, @RequestParam(defaultValue = "")String title, @RequestParam(defaultValue = "")String isbn){
        setFilterState(authors, title, isbn);
        if (isFilteredByAuthor){
            model.addAttribute("books", bookServiceProxy.getBookByAuthorsByKeyWord(param));
        }
        else if (isFilteredByTitle){
            model.addAttribute("books", bookServiceProxy.getBookByTitleByKeyWord(param));
        }
        else if (isFilteredByIsbn) {
            model.addAttribute("books", bookServiceProxy.getBookByIsbn(param));
        }
        else {
            model.addAttribute("books", bookServiceProxy.getBooks());
        }
        resetFilterState();
        return "book";
    }
}