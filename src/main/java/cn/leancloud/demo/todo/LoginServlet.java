package cn.leancloud.demo.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    req.getRequestDispatcher("/login.jsp").forward(req, resp);
  }


  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String username = req.getParameter("username");
    String passwd = req.getParameter("password");
    try {
      AVUser.logIn(username, passwd);
      req.getRequestDispatcher("/profile").forward(req, resp);
    } catch (AVException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      resp.setContentType("application/json; charset=UTF-8");
      JSONObject result = new JSONObject();
      result.put("code", e.getCode());
      result.put("error", e.getMessage());
      resp.getWriter().write(result.toJSONString());
      e.printStackTrace();
    }
  }
}
