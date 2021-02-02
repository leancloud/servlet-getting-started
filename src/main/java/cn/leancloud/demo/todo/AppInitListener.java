package cn.leancloud.demo.todo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.leancloud.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.leancloud.core.AVOSCloud;
import cn.leancloud.core.GeneralRequestSignature;

@WebListener
public class AppInitListener implements ServletContextListener {

  private static final Logger logger = LogManager.getLogger(AppInitListener.class);

  private String appId = System.getenv("LEANCLOUD_APP_ID");
  private String appKey = System.getenv("LEANCLOUD_APP_KEY");
  private String appMasterKey = System.getenv("LEANCLOUD_APP_MASTER_KEY");
  private String hookKey = System.getenv("LEANCLOUD_APP_HOOK_KEY");
  private String appEnv = System.getenv("LEANCLOUD_APP_ENV");
  private String haveStaging = System.getenv("LEAN_CLI_HAVE_STAGING");

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {}

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    logger.info("LeanEngine app init.");
    // Enables debug logging.
    AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);
    // Registers subclass.
    AVObject.registerSubclass(Todo.class);

    if ("development".equals(appEnv) && "true".equals(haveStaging) || "stage".equals(appEnv)) {
      AVCloud.setProductionMode(false);
    }
    // Initializes application.
    // Ensure that you only perform one initialization in the whole project.
    LeanEngine.initialize(appId, appKey, appMasterKey, hookKey);
    // Uses masterKey for the whole project.
    GeneralRequestSignature.setMasterKey(appMasterKey);

    // If you don't need session cookie, you can comment following line.
    LeanEngine.addSessionCookie(new EngineSessionCookie("my secret", 3600, false));

    // Registers cloud functions.
    LeanEngine.register(Cloud.class);
  }
}
