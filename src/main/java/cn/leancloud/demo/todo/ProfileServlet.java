package cn.leancloud.demo.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVUser;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    if (AVUser.getCurrentUser() == null) {
      req.getRequestDispatcher("/login").forward(req, resp);
    } else {
      resp.setContentType("application/json; charset=UTF-8");
      JSONObject result = new JSONObject();
      result.put("currentUser", AVUser.getCurrentUser());
      resp.getWriter().write(result.toJSONString());
    }
  }
}
