package com.amrit.first.controllers;

import com.amrit.first.config.LCWDconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    @Value("${lcwd.profile.image.path}")
    private String profilePath;

    @Autowired
    private LCWDconfig lcwDconfig;


    @RequestMapping("/todos")
    public List<String> justTest(){

        List<String> todos = Arrays.asList(
                "learn springboot","Develop project",
        "learndjango","learnspring","learnstsnfsa");

        return todos;
    }

    @RequestMapping("/profile-path")
    public String getProfilePath(){
        return this.profilePath;


    }

    @RequestMapping("/lcwdconfig")
    public  LCWDconfig getLcwDconfig(){
        System.out.println(this.lcwDconfig);
        return this.lcwDconfig;
    }


}
