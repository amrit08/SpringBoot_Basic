package com.docker.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @RequestMapping("/")
    public Map<String ,Object> getValues(){

        HashMap<String, Object> data = new HashMap<>();
        data.put("message","Java api is working fine");
        data.put("languages", Arrays.asList("Java","Python","Ruby"));
        data.put("code",2345);
        return data;
    }





}
