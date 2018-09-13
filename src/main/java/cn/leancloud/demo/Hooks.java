package cn.leancloud.demo;

import cn.leancloud.EngineHook;
import cn.leancloud.EngineHookType;
import com.avos.avoscloud.AVUser;

public class Hooks {

  @EngineHook(className = "_User", type = EngineHookType.afterSave)
  public static void afterSaveUser(AVUser newUser) {
    System.out.println("New user: " + newUser.getUsername());
  }

}
