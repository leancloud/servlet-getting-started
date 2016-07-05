package cn.leancloud.demo.todo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leancloud.LeanEngine;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVOSServices;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.PaasClient;
import com.avos.avoscloud.internal.impl.EngineRequestSign;

@WebServlet(name = "AppServlet", urlPatterns = {"/todos"}, loadOnStartup = 1)
public class TodoServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    super.init();
    // 注册子类化
    AVObject.registerSubclass(Note.class);
    // 初始化AVOSCloud
    AVOSCloud.initialize("H7KfzUELCqSPuWoABh1x9017-gzGzoHsz", "M8Fz60Rvmwir438zsu1Dg2WJ",
        "b4Rb7tfCpa2w4zinCfPwRuu1");
    // 在请求签名中使用masterKey以激活云代码的最高权限
    EngineRequestSign.instance().setUserMasterKey(true);
    // 打开日志
    AVOSCloud.setDebugLogEnabled(true);
    // 向云引擎注册云函数
    LeanEngine.register(TodoFunctions.class);
    setLocalEngineAddress();
  }

  public static void setLocalEngineAddress() {
    try {
      Method setFunctionUrl =
          PaasClient.class.getDeclaredMethod("setServiceHost", AVOSServices.class, String.class);
      setFunctionUrl.setAccessible(true);
      setFunctionUrl.invoke(null, AVOSServices.FUNCTION_SERVICE, "http://0.0.0.0:3000");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String offsetParam = req.getParameter("offset");
    int offset = 0;
    if (!AVUtils.isBlankString(offsetParam)) {
      offset = Integer.parseInt(offsetParam);
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("offset", offset);
    try {
      List<Note> data = AVCloud.rpcFunction("list", params);
      req.setAttribute("todos", data);

    } catch (AVException e) {
      e.printStackTrace();
    }
    req.getRequestDispatcher("/todos.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String content = req.getParameter("content");

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("content", content);
    try {
      AVCloud.rpcFunction("save", params);
    } catch (AVException e) {
      e.printStackTrace();
    }
    resp.sendRedirect("/todos");
  }
}
