package cn.leancloud.demo.todo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.leancloud.LeanEngine;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.internal.impl.EngineRequestSign;

@WebListener
public class TodoInitListener implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {

  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    // 注册子类化
    AVObject.registerSubclass(Note.class);
    // 初始化AVOSCloud，请保证在整个项目中间只初始化一次
    AVOSCloud.initialize("H7KfzUELCqSPuWoABh1x9017-gzGzoHsz", "M8Fz60Rvmwir438zsu1Dg2WJ",
        "b4Rb7tfCpa2w4zinCfPwRuu1");
    // 在请求签名中使用masterKey以激活云代码的最高权限
    EngineRequestSign.instance().setUserMasterKey(true);
    // 打开日志
    AVOSCloud.setDebugLogEnabled(true);
    // 向云引擎注册云函数
    LeanEngine.register(TodoFunctions.class);
    LeanEngine.setLocalEngineCallEnabled(true);
  }
}
