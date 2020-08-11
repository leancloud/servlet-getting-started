package cn.leancloud.demo.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leancloud.AVException;
import cn.leancloud.AVObject;
import cn.leancloud.AVQuery;
import cn.leancloud.utils.StringUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@WebServlet(name = "AppServlet", urlPatterns = { "/todos" })
public class TodoServlet extends HttpServlet {

  private static final long serialVersionUID = -225836733891271748L;

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    String offsetParam = req.getParameter("offset");
    int offset = 0;
    if (!StringUtil.isEmpty(offsetParam)) {
      offset = Integer.parseInt(offsetParam);
    }
    AVQuery<Todo> query = AVObject.getQuery(Todo.class);
    query.orderByDescending("createdAt");
    query.skip(offset);

    query.findInBackground().subscribe(new Observer<List<Todo>>() {
      @Override
      public void onSubscribe(Disposable disposable) {

      }

      @Override
      public void onNext(List<Todo> todos) {
        req.setAttribute("todos", todos);
        try {
          req.getRequestDispatcher("/todos.jsp").forward(req, resp);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

      @Override
      public void onError(Throwable throwable) {
        if (throwable instanceof AVException) {
          if (((AVException) throwable).getCode() == 101) {
            // Todo class does not exist in the cloud yet.
            req.setAttribute("todos", new ArrayList<>());
          }
          req.setAttribute("todos", new ArrayList<>());
          try {
            req.getRequestDispatcher("/todos.jsp").forward(req, resp);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
//          throw new RuntimeException(throwable);
        }
      }

      @Override
      public void onComplete() {

      }
    });
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String content = req.getParameter("content");

    AVObject note = new Todo();
    note.put("content", content);
    note.save();
    resp.sendRedirect("/todos");
  }
}
