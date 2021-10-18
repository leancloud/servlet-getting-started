package cn.leancloud.demo.todo;

import java.util.HashMap;
import java.util.Map;


import cn.leancloud.annotation.LCClassName;
import cn.leancloud.LCObject;
import cn.leancloud.json.JSON;

@LCClassName("Todo")
public class Todo extends LCObject {

  public String getContent() {
    return getString("content");
  }

  @Override
  public String toString() {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("content", this.getString("content"));
    result.put("objectId", this.getObjectId());
    result.put("createdAt", this.getCreatedAt());
    return JSON.toJSONString(result);
  }
}
