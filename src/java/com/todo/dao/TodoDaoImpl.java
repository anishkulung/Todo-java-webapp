package com.todo.dao;

import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import com.todo.dao.TodoDao;
import com.todo.model.Todo;
import com.todo.utils.DBUtils;
import java.util.ArrayList;

public class TodoDaoImpl implements TodoDao{
    
    private static String INSERT_TODO = "INSERT INTO todos"
			+ "  (task, assign_to, completed) VALUES " + " (?, ?, ?)";
    private static String GET_TODO_BY_ID = "SELECT * FROM todos WHERE id =?";
    private static String GET_ALL_TODOS = "SELECT * FROM todos";
    private static String DELETE_TODO_BY_ID = "DELETE FROM todos WHERE id = ?";
    private static String UPDATE_TODO = "UPDATE todos SET task = ?, assign_to = ?, completed = ? WHERE id = ?";
    private static String GET_TODO_BY_TASK = "SELECT * FROM todos WHERE task LIKE CONCAT('%',?,'%')";
    
    
     //insert
    @Override
    public void insertTodo(Todo task) throws SQLException {
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(INSERT_TODO)) {
            preparedStatement.setString(1,task.getTask());
            preparedStatement.setString(2,task.getAssign_to());
            preparedStatement.setBoolean(3,task.isCompleted());
            preparedStatement.executeUpdate();
           
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    //update
    @Override
    public boolean updateTodo(Todo task) throws SQLException{
        boolean rowUpdated = false;
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_TODO);) {
            preparedStatement.setString(1,task.getTask());
            preparedStatement.setString(2,task.getAssign_to());
            preparedStatement.setBoolean(3,task.isCompleted());
            preparedStatement.setInt(4,task.getTodoId());
            
            rowUpdated = preparedStatement.executeUpdate() > 0;
           
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rowUpdated;
    }
    
    //delete
    @Override
    public boolean deleteTodo(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection conn = DBUtils.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(DELETE_TODO_BY_ID);){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    @Override
    public List<Todo> getAllTodos() {

        List<Todo> todos = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
                ) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TODOS);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                String assign_to = rs.getString("assign_to");
                boolean isCompleted = rs.getBoolean("completed");
                todos.add(new Todo(id,task, assign_to, isCompleted));
                System.out.println(id+task);
            }
            
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return todos;
    }
    public static void main(String[] args) {
        TodoDaoImpl t = new TodoDaoImpl();
        t.getAllTodos();
    }
    
    //getone
    @Override
    public Todo getTodoById(int id){
        Todo todo = null;
        try (Connection connection = DBUtils.getConnection();
                ) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TODO_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int todoId = rs.getInt("id");
                String task = rs.getString("task");
                String assign_to = rs.getString("assign_to");
                boolean isCompleted = rs.getBoolean("completed");
                todo = new Todo(todoId,task, assign_to, isCompleted);
                
            }
            
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return todo;
    }

     //search
    @Override
    public List<Todo> searchTodoByTask(String search){
         List<Todo> todos = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
                ) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TODO_BY_TASK);
            preparedStatement.setString(1, search);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                String assign_to = rs.getString("assign_to");
                boolean isCompleted = rs.getBoolean("completed");
                todos.add(new Todo(id,task, assign_to, isCompleted));
            }
            
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return todos;
    }

}
