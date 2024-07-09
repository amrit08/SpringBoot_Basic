package com.apj.todo;

import com.apj.todo.dao.TodoDao;
import com.apj.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);

	@Autowired
	private TodoDao todoDao;

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println("Application Started :");
//
//		JdbcTemplate template = todoDao.getTemplate();
//
//		logger.info("Template Object : {}",template);

//		Todo todo = new Todo();
//		todo.setId(13);
//		todo.setTitle("This is Python Bacened Development course");
//		todo.setContent("learn pyhton jdkjadnbdac ");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//		todoDao.saveTodo(todo);

//
//		Todo todo = todoDao.getTodo(13);
//		logger.info("TODO :{}",todo);

//		todo.setTitle("Learn SpringBoot Course");
//		todo.setContent("I have to learn apring boot");
//		todo.setStatus("DONE");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//		todoDao.updateTodo(1230,todo);
//
//		List<Todo> allTodos = todoDao.getAllTodos();
//		logger.info("ALL TODOS : {}",allTodos);


//		todoDao.deleteTodo(1230);

	//	todoDao.deleteMultiple(new int[]{123,132,234});



	}
}
