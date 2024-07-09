package com.apj.mvc.SpringMvcProject.controllers;

import com.apj.mvc.SpringMvcProject.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {


//    @PostMapping("/create")
//    public String createStudent(@RequestBody Map<String,Object> data){
//        System.out.println(data);
//        Object name = data.get("name");
//        Object age = data.get("age");
//        System.out.println("Name"+name);
//        System.out.println("age"+age);
//
//    return "created";
//    }
//    @PostMapping("/create")
//    public String createStudent(@RequestBody Student student){
//
//        System.out.println(student);
//
//    return "created";
//    }

    @PostMapping("/create")
    public ResponseEntity<List<Student>> createStudent(@RequestBody List<Student> students){

        System.out.println("Student list size"+students.size());
        System.out.println(students);

       // List<String> foods = Arrays.asList("Pissa","kjaka","uwqf");

        //return foods;

       // return students;

//        Map<String ,Object> data =  new HashMap<>();
//        data.put("content",students);
//        data.put("error","not error found");
//        data.put("current date",new Date());
//       data.put("systemInformation",System.getProperties());

//        Student student = students.get(0);
//        student.setRollno(12345);
//        String str = null;
//        System.out.println(str.length());

        ResponseEntity<List<Student>> responseEntity = new ResponseEntity<>(students, HttpStatus.OK);

        return responseEntity ;
    }

}
