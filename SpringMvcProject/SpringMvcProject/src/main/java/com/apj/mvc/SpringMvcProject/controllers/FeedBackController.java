package com.apj.mvc.SpringMvcProject.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class FeedBackController {
    //@RequestMapping(value = "/feedbacks",method = RequestMethod.GET)
    @GetMapping("/feedbacks")
    public List<String> getFeedbacks(){
        List<String> feedbacks = Arrays.asList(
                "Good course",
                "Nice one",
                "jajlajd",
                "jsnan"
        );

        return feedbacks;
    }

    @PostMapping("/create-feedback")
    public String createFeedback(){
        System.out.println("Feedback created");
        return "created feedback";

    }

}
