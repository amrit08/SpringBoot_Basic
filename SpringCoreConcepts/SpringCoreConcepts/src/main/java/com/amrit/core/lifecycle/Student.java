package com.amrit.core.lifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("student5")
public class Student {
    public  Student(){

        System.out.println("#:Creating student");
    }
    @PostConstruct
    public void postConstruct() {

        System.out.println("#:student bean is created:created()");
    }

    @PreDestroy
    public void preDestroy(){

        System.out.println("#:bye bye student :destroy()");
    }



}
