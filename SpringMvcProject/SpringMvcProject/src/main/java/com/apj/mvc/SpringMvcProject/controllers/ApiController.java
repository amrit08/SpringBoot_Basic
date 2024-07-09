package com.apj.mvc.SpringMvcProject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String helloApi()
    {
        return "hello how are you what is this ";

    }
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<String> getUserData(){
        return Arrays.asList("amrit","pandit","panditain");
    }

    @RequestMapping(value = "/create-user",method = RequestMethod.POST)
    public  String createUser(){

        System.out.println("creating user");
        return "user created succesfsadk";
    }
}
