package com.apj.mvc.SpringMvcProject.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Component
//@RequestMapping
@Controller
public class SuperMan {

    @RequestMapping("/about")
    public String aboutRequestHandler() {
        System.out.println("processiing about request");
        return "about";
    }

    @RequestMapping("/services")
    public String serviceRequestHandler() {
        System.out.println("processing service request");
        return "services";

    }
}
