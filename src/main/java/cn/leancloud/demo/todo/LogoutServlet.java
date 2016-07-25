package cn.leancloud.demo.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avos.avoscloud.AVUser;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    AVUser user = AVUser.getCurrentUser();
    if (user != null) {
      user.logOut();
    }
    req.getRequestDispatcher("/profile").forward(req, resp);
  }
}
