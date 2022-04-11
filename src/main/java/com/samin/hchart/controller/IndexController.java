package com.samin.hchart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/main/content")
    public String layoutDefault(){
        return "main/content";
    }

    @GetMapping(value = "/main/home")
    public String home() {
        return "main/home";
    }
}
