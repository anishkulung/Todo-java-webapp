package com.todo.model;


public class Todo {

    private int todoId;
    private String task;
    private String assign_to;
    private boolean completed;
    
    protected Todo(){
        
    }
    
    public Todo(String task, String assign_to, boolean completed) {
        super();
        this.task = task;
        this.assign_to = assign_to;
        this.completed = completed;
    }

    
    public Todo(int id, String task, String assign_to, boolean completed) {
        super();
        this.todoId = id;
        this.task = task;
        this.assign_to = assign_to;
        this.completed = completed;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssign_to() {
        return assign_to;
    }

    public void setAssign_to(String assign_to) {
        this.assign_to = assign_to;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
}
