package com.apj.todo.dao;
import com.apj.todo.helper.Helper;
import com.apj.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {
    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        //create table if does not exits

        String createTable = "create table IF NOT EXISTS todos(id int primary key ,title varchar(100) NOT NULL , content varchar(500),status varchar(10) not null ,addedDate datetime , toDoDate datetime)";
        int update = template.update(createTable);

        logger.info("TODO TABLE CREATED {}", update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
    public Todo saveTodo(Todo todo) {
        String insertQuery = "insert into todos(id,title,content,status,addedDate,toDoDate) values(?,?,?,?,?,?)";

        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getToDoDate());

        logger.info("JDBC OPERATION {} inserted", rows);

        return todo;
    }

    //get single todo from database
        public  Todo  getTodo(int id) throws ParseException {
            String query = "Select * from todos WHERE id = ?";
          //  Map<String, Object> todoData = template.queryForMap(query, id);

           Todo todo = template.queryForObject(query, new RowMapper<Todo>() {
               @Override
               public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                   Todo todo = new Todo();

                   todo.setId((rs.getInt("id")));
                   todo.setTitle((rs.getString("title")));
                   todo.setContent((rs.getString("content")));
                   todo.setStatus((rs.getString("status")));

                   try {
                       todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }
                   try {
                       todo.setToDoDate(Helper.parseDate((LocalDateTime)rs.getObject("toDoDate")));
                   } catch (ParseException e) {
                       throw new RuntimeException(e);
                   }

                   return todo;
               }

           }, id);

//            Todo todo = new Todo();
//            todo.setId(((int)todoData.get("id")));
//            todo.setTitle(((String)todoData.get("title")));
//            todo.setContent(((String)todoData.get("content")));
//            todo.setStatus(((String)todoData.get("status")));
//
//            todo.setAddedDate(Helper.parseDate((LocalDateTime)todoData.get("addedDate")));
//            todo.setToDoDate(Helper.parseDate((LocalDateTime)todoData.get("toDoDate")));
             return todo;
        }

    // get all todo from database

    public List<Todo> getAllTodos(){
        String query = "Select * from todos";
        List<Todo> todo = template.query(query, (rs,rowNum) -> {

            Todo todo1 = new Todo();

            todo1.setId((rs.getInt("id")));
            todo1.setTitle((rs.getString("title")));
            todo1.setContent((rs.getString("content")));
            todo1.setStatus((rs.getString("status")));

            try {
                todo1.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                todo1.setToDoDate(Helper.parseDate((LocalDateTime)rs.getObject("toDoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            return todo1;

        });
        return  todo;



        //   List<Map<String, Object>> maps = template.queryForList(query);

//        List<Todo> todos = maps.stream().map((map) -> {
//
//            Todo todo = new Todo();
//            todo.setId(((int) map.get("id")));
//            todo.setTitle(((String) map.get("title")));
//            todo.setContent(((String) map.get("content")));
//            todo.setStatus(((String) map.get("status")));
//
//            try {
//                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("toDoDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            return todo;
//
//        }).collect(Collectors.toList());


   // return  todos;
    }

    //update todo
    public Todo updateTodo(int id ,Todo newTodo){
        String query = "update todos set title=?,content=?,status=?,addedDate=?,toDoDate=? WHERE id =?";
        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getToDoDate(),id);
        logger.info("UPDATED {}",update);
        newTodo.setId(id);
        return  newTodo;
    }


    //delete todo from databse
    public void deleteTodo(int id){
        String query = "delete from todos WHERE id =?";
        int update = template.update(query, id);
        logger.info("DELETED {}",update);


    }
    public  void  deleteMultiple(int ids[]){
        String query = "delete from todos WHERE id =?";
        //forEach->
        //template.batchUpdate(query,ids);
        int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);


            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });
        for(int i:ints)
        {
            logger.info("DELETED {} :",i);

        }



    }










}
