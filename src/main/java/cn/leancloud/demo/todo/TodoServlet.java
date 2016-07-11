package cn.leancloud.demo.todo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUtils;

@WebServlet(name = "AppServlet", urlPatterns = {"/todos"})
public class TodoServlet extends HttpServlet {

  private static final long serialVersionUID = -225836733891271748L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String offsetParam = req.getParameter("offset");
    int offset = 0;
    if (!AVUtils.isBlankString(offsetParam)) {
      offset = Integer.parseInt(offsetParam);
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("offset", offset);
    try {
      List<Todo> data = AVCloud.rpcFunction("list", params);
      req.setAttribute("todos", data);

    } catch (AVException e) {
      e.printStackTrace();
    }
    req.getRequestDispatcher("/todos.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
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
