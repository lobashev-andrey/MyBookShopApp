package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookshop")
public class AuthorsPageController {

    private final BookService bookService;

    @Autowired
    public AuthorsPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/authors")
    public String authorsPage(Model model){
        model.addAttribute("authorData", bookService.getAuthorData());
        return "/authors/index";
    }

}
