package cn.leancloud.demo.todo;

import java.util.List;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUtils;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;

public class TodoFunctions {

  @EngineFunction("save")
  public static void saveNote(@EngineFunctionParam("content") String content) throws Exception {
    if (AVUtils.isBlankString(content)) {
      throw new Exception("can't create empty");
    } else {
      Note object = new Note();
      object.put("content", content);
      object.save();
    }
  }

  @EngineFunction("list")
  public static List<AVObject> getNotes(@EngineFunctionParam("offset") int offset) throws Exception {
    AVQuery<AVObject> query = new AVQuery("Note");
    query.orderByDescending("createdAt");
    query.include("createdAt");
    query.skip(offset);
    List<AVObject> result = query.find();
    System.out.println(result.get(0).getCreatedAt());
    return result;
  }
}
