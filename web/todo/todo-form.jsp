<%-- 
    Document   : todo-form
    Created on : Mar 9, 2022, 9:58:12 PM
    Author     : anish kulung rai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                font-size: 18px;
                 
            }
            button{
                font-size: 18px;
                padding: 5px 15px;
            }
            input,select{
                border: 2px solid;
                border-radius: 4px;
                width: 100%;
                padding: 10px 15px;
                box-sizing: border-box;
                font-size: 16px;
            }
            
            .list, th,td{
                border-collapse: collapse;
              
                margin: auto;
            }
        </style>
    </head>
    <body>
        
       
            <table class="list" border="2" cellpadding="10px" cellspacing="5px" width="50%">
            <c:if test="${todo != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${todo == null}">
                <form action="insert" method="post">
            </c:if>
                    <c:if test="${todo != null}">
                        <input type="hidden" name ="id" value="<c:out value='${todo.todoId}'/>"/>
                    </c:if>
                            <tr>
                                <td><label>Task: </label></td> 
                                <td><input type="text" value="<c:out value='${todo.task}' />"
                                           name="task" required="required"></td> 
                           </tr><br>
                            <tr>
                                <td><label>Assign To: </label> </td>
                                <td><input type="text" value="<c:out value='${todo.assign_to}' />" 
                                           name="assign_to" required="required"></td>
                            </tr><br>
                            <tr>
                                <td><label>Status: </label></td>
                                <td><select name="isCompleted">
                                        <option value="false">In Progress</option>
                                        <option value="true">Completed</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><button type="submit">Add</button></td>
                            </tr>
                </form>
            </table>


    </body>
</html>
