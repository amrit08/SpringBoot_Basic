package com.apj.todo.controllers;

import com.apj.todo.models.Todo;
import com.apj.todo.services.TodoService;
import com.apj.todo.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    Random random = new Random();

    //create
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

//        String str = null;
//        logger.info("{}",str.length());

    //    Integer.parseInt("123jakajn");

        int id = random.nextInt(999999);
        todo.setId(id);
        //create date with system default current date
        Date currentdate = new Date();
        logger.info("current date: {}",currentdate);
        logger.info("toDo date {}",todo.getToDoDate());

        logger.info("Create todo");
        todo.setAddedDate(currentdate);
        //call service to create todo
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //get all todos
    @GetMapping
    public  ResponseEntity<List<Todo>> getAllTodoHandler()
    {
        List<Todo> allTodos = todoService.getAllTodos();
        return  new ResponseEntity<>(allTodos,HttpStatus.OK);

    }

    //get single todo
    @GetMapping("/{todoId}")
    public  ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);

    }
    // update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todowithNewDetails,
                                                  @PathVariable int todoId
                                                  ){

        Todo todo = todoService.updateTodo(todoId, todowithNewDetails);
        return  ResponseEntity.ok(todo);

    }
    //Delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){

        todoService.deleteTodo(todoId);

        return  ResponseEntity.ok("Todo Successfully deleted ");

    }
//    //Exception handler
//    @ExceptionHandler(value = {NullPointerException.class , NumberFormatException.class})
//    public ResponseEntity<String> nullPointerExceptionHandler(Exception ex){
//
//        logger.info("{}",ex.getMessage());
//        logger.info("null pointer exception generated");
//        return new ResponseEntity<>("Null pointer exception generated"+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//    }

//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> numberFormatExceptionHandler(NumberFormatException ex){
//
//        logger.info("{}",ex.getMessage());
//        logger.info("number format exception generated");
//        return new ResponseEntity<>("Number format exception generated"+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//    }


}
