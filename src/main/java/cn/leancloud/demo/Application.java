package cn.leancloud.demo;

import cn.leancloud.LeanEngine;

public class Application {

  public static void main(String[] args) throws Exception {
    LeanEngine engine = new LeanEngine().register(new Class[]{CloudFunctions.class, Hooks.class});
    engine.start();
  }
}
