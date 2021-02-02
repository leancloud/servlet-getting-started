<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>UserProfile - LeanEngine Demo</title>
<link rel="stylesheet" href="/stylesheets/style.css" />
</head>
<body>
	<p>Current User Profile: ${currentUser}</p>

	 <form action="/logout" method="post">
       <input class="button" type="submit" value="登出"/>
     </form>
</body>
</html>