package cn.leancloud.demo;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;

public class CloudFunctions {

  @EngineFunction("hello")
  public static String hello(@EngineFunctionParam("name") String name) {
    if (name == null) {
      return "What is your name?";
    }
    return String.format("Hello %s!", name);
  }

}
