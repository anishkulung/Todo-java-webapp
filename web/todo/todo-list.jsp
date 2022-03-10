<%-- 
    Document   : todo-list.jsp
    Created on : Mar 9, 2022, 5:12:56 PM
    Author     : anish kulung rai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todo List</title>
        <style>
            body{
                box-sizing: content-box;
                padding: 20px;
            }
            .list{
               
                border: 2px solid;
                padding: 10px;
                font-size: 18px;
                margin:auto;
                align:center;
            }
            tr,td,th{
                border-collapse: collapse;
                text-align: left;
                border: 1px solid;
            }
            
            a{
                font-size: 18px;
                padding: 15px 15px;
                
            }
            input{
                width: 95%;
                padding: 5px;
                border: none;
                border-bottom: 2px solid;
                
            }
            table{
                margin: auto;
                
                padding: 10px;
                font-size: 18px;
                margin:auto;
                align:center;
            }
            button{
                text-align: center;
                font-weight: bold;
                font-size: 16px;
                align-content: center;
            }
        </style>
    </head>
    <body>
        <h1>Todo List!!!</h1>


        <table width="50%" cellpadding="5px">
        <form action="search" method="post">
            <tr>
                <td><input type="text" name="searchText" value=""/></td>
                <td class="search"> <button type="submit">Search</button></td>
            </tr>
        </form>
            
        </table><br>
        <table class="list" cellpadding="10px" cellspacing="5px" width="70%" >
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Task</th>
                    <th>Assigned To</th>
                    <th>Completed</th>
                    <th >Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var = "todo" items = "${listTodo}">
                    <tr>
                        
                        
                        <td><c:out value="${todo.todoId}"/></td>
                        <td><c:out value="${todo.task}"/></td>
                        <td><c:out value="${todo.assign_to}"/></td>
                        <td><c:out value="${todo.completed}"/></td>
                        <td align="center"><a href="edit?id=<c:out value='${todo.todoId}'/>">Edit</a>&nbsp;
                        <a href="delete?id=<c:out value='${todo.todoId}'/>">Delete</a></td>
                        
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="6">
                            <button><a href="<%=request.getContextPath()%>/add-todo">Add Todo</a></button>
                        </td>
                    </tr>
            </tbody>
        </table>

    </body>
</html>

