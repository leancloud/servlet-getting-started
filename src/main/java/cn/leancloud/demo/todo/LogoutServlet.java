package cn.leancloud.demo.todo;

import cn.leancloud.AVUser;
import cn.leancloud.EngineRequestContext;
import cn.leancloud.EngineSessionCookie;
import cn.leancloud.LeanEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
          IOException {
    doPost(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
          IOException {
    // get authenticated user from request cookie.
    AVUser user = EngineRequestContext.getAuthenticatedUser();
    if (user != null) {
      user.logOut();
    }
    // delete cookie/session for next login.
    EngineRequestContext.setAuthenticatedUser(null);
    EngineSessionCookie sessionCookie = LeanEngine.getSessionCookie();
    if (null != sessionCookie) {
      sessionCookie.wrapCookie(true);
    }
    resp.sendRedirect("/login");
  }

}
