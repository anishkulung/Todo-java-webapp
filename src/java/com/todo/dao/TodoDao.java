package com.todo.dao;

import java.sql.SQLException;
import java.util.List;

import com.todo.model.Todo;


public interface TodoDao {
    
    //insert
    void insertTodo(Todo task) throws SQLException;
    
    //update
    boolean updateTodo(Todo task) throws SQLException;
    
    //delete
    boolean deleteTodo(int id) throws SQLException;
    
    //getone
    Todo getTodoById(int id);
    
    //getall
    List<Todo> getAllTodos();
    
//    //search
    List<Todo> searchTodoByTask(String search);

}
