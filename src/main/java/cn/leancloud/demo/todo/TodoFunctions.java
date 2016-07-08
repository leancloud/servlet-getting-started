package cn.leancloud.demo.todo;

import java.util.List;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;

public class TodoFunctions {

  @EngineFunction("list")
  public static List<AVObject> getNotes(@EngineFunctionParam("offset") int offset)
      throws Exception {
    AVQuery<AVObject> query = new AVQuery<>("Note");
    query.orderByDescending("createdAt");
    query.include("createdAt");
    query.skip(offset);
    List<AVObject> result = query.find();
    return result;
  }
}
