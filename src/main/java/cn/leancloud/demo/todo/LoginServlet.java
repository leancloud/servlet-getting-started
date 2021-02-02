package cn.leancloud.demo.todo;

import cn.leancloud.*;
import cn.leancloud.json.JSON;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
          IOException {
    req.getRequestDispatcher("/login.jsp").forward(req, resp);
  }

  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
    String username = req.getParameter("username");
    String passwd = req.getParameter("password");
    AVUser.logIn(username, passwd).subscribe(new Observer<AVUser>() {
      @Override
      public void onSubscribe(Disposable disposable) {

      }

      @Override
      public void onNext(AVUser avUser) {
        try {
          // save user info to cookie and local session.
          EngineRequestContext.setAuthenticatedUser(avUser);
          EngineSessionCookie sessionCookie = LeanEngine.getSessionCookie();
          if (null != sessionCookie) {
            sessionCookie.wrapCookie(true);
          }

          resp.sendRedirect("/profile");
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable throwable) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType("application/json; charset=UTF-8");
        Map<String, Object> result = new HashMap<>();
        result.put("error", throwable.getMessage());
        try {
          resp.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }

      @Override
      public void onComplete() {

      }
    });

  }
}
