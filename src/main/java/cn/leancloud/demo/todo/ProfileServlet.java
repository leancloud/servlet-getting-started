package cn.leancloud.demo.todo;

import cn.leancloud.LCUser;
import cn.leancloud.EngineRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
          IOException {
    // get authenticated user from request cookie.
    LCUser currentUser = EngineRequestContext.getAuthenticatedUser();
    if (null != currentUser) {
      req.setAttribute("currentUser", currentUser);
    } else {
      System.out.println("current User is empty.");
    }
    try {
      req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
