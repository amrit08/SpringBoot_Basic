package com.apj.mvc.SpringMvcProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdimnController {

    @RequestMapping("/login")
    public String loginPageHandler(){
        System.out.println("processing login page");
        return "login";

    }


}
