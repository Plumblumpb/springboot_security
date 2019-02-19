package com.plumblum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/index.html")
    public String test(){
        return "index.html";
    }
}
