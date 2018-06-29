package cn.leancloud.demo.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUtils;

@WebServlet(name = "AppServlet", urlPatterns = { "/todos" })
public class TodoServlet extends HttpServlet {

  private static final long serialVersionUID = -225836733891271748L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String offsetParam = req.getParameter("offset");
    int offset = 0;
    if (!AVUtils.isBlankString(offsetParam)) {
      offset = Integer.parseInt(offsetParam);
    }
    try {
      AVQuery<Todo> query = AVObject.getQuery(Todo.class);
      query.orderByDescending("createdAt");
      query.include("createdAt");
      query.skip(offset);
      req.setAttribute("todos", query.find());

    } catch (AVException e) {
      if (e.getCode() == AVException.OBJECT_NOT_FOUND) {
        // 该错误的信息为：{ code: 101, message: 'Class or object doesn\'t exists.' }，说明 Todo
        // 数据表还未创建，所以返回空的
        // Todo 列表。
        // 具体的错误代码详见：https://leancloud.cn/docs/error_code.html
        req.setAttribute("todos", new ArrayList<>());
      } else {
        throw new RuntimeException(e);
      }
    }
    req.getRequestDispatcher("/todos.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String content = req.getParameter("content");

    try {
      AVObject note = new Todo();
      note.put("content", content);
      note.save();
    } catch (AVException e) {
      e.printStackTrace();
    }
    resp.sendRedirect("/todos");
  }
}
