package cn.leancloud.demo.todo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("Todo")
public class Todo extends AVObject {

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
