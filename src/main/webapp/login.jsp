<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>UserLogin - LeanEngine Demo</title>
  <link rel="stylesheet" href="/stylesheets/style.css" />
  </head>
  <body>
    <form method="post" action="/login">
      <label>Username</label>
      <input name="username">
      <label>Password</label>
      <input name="password" type="password">
      <input class="button" type="submit" value="登录">
    </form>
  </body>
</html>