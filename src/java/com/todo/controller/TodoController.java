package com.todo.controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;

import com.todo.dao.TodoDao;
import com.todo.dao.TodoDaoImpl;
import com.todo.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {

    private TodoDao todoDao;
    
    @Override
    public void init(){
        todoDao  = new TodoDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/add-todo":
                    showTodoForm(request, response);
                    break;
                case "/list":
                    listTodo(request, response);
                    break;
                    
                case "/delete":
                    deleteTodo(request, response);
                    break;
                case "/edit":
                    showUpdateForm(request, response);
                    break;
                    
                case "/update":
                    updateTodo(request, response);
                    break;
                
                case "/search":
                    showSearchResults(request, response);
                    break;

                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
	List<Todo> listTodo = todoDao.getAllTodos();
	request.setAttribute("listTodo", listTodo);
	RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
	dispatcher.forward(request, response);
	}
    
    private void showTodoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Todo todo = todoDao.getTodoById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        request.setAttribute("todo", todo);
        dispatcher.forward(request, response);
        
    }
    
    private void showSearchResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String searchQuery = request.getParameter("searchText");
        System.out.println(searchQuery);
        List<Todo> listTodo = todoDao.searchTodoByTask(searchQuery);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        request.setAttribute("listTodo", listTodo);
        dispatcher.forward(request, response);
    }
    
    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	String task = request.getParameter("task");
	String name = request.getParameter("assign_to");
	boolean completed = Boolean.valueOf(request.getParameter("isCompleted"));
	Todo newTodo = new Todo(task, name, completed);
	todoDao.insertTodo(newTodo);
        response.sendRedirect("list");
    }
    
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        todoDao.deleteTodo(Integer.parseInt(id));
        response.sendRedirect("list");
    }
    
    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String task = request.getParameter("task");
        String assign_to = request.getParameter("assign_to");
        boolean completed = Boolean.valueOf(request.getParameter("isCompleted"));
        todoDao.updateTodo(new Todo(id,task, assign_to, completed));
        response.sendRedirect("list");
    }
}
