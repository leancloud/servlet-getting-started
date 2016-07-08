<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>LeanEngine</title>
<link rel="stylesheet" href="/stylesheets/style.css" />
</head>
<body>
 <h1>TODO 列表</h1>
 <form action="/todos" method="post">
   <input type="text" name="content"/>
   <input type="submit" />
 </form>

<span>
<table>
<thead>
    <tr>
        <td><h3>content</h3></td>
        <td><h3>objectId</h3></td>
        <td><h3>createdAt</h3></td>
    </tr>
</thead>
<tbody>
<c:forEach items="${todos}" var="todo">
    <tr>      
        <td>${todo.content}</td>
        <td>${todo.objectId}</td>
        <td>${todo.createdAt}</td>
    </tr>    
</c:forEach>
</tbody>
</table>
</span>
</body>
</html>
