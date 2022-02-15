package cn.leancloud.demo.todo;

import cn.leancloud.*;
import cn.leancloud.json.JSONObject;
import cn.leancloud.sms.LCSMS;
import cn.leancloud.sms.LCSMSOption;
import cn.leancloud.types.LCNull;
import cn.leancloud.utils.StringUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

public class Cloud {

  @EngineFunction("hello")
  public static String hello(@EngineFunctionParam("name") String name) {
    if (name == null) {
      return "What is your name?";
    }
    // the following code just does demonstrate how to get authenticated user's sessionToken.
    String authUserSessionToken = EngineRequestContext.getSessionToken();
    System.out.println("authUserSessionToken: " + authUserSessionToken);

    return String.format("Hello %s!", name);
  }

  @EngineFunction("sendSMS")
  public static String sendSMS(@EngineFunctionParam("mobile") String mobile) {
    if (StringUtil.isEmpty(mobile)) {
      return "What is your name?";
    }
    LCSMSOption option = new LCSMSOption();
    LCSMS.requestSMSCodeInBackground(mobile, option).subscribe(new Observer<LCNull>() {
      @Override
      public void onSubscribe(Disposable disposable) {

      }

      @Override
      public void onNext(LCNull avNull) {
        ;
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onComplete() {

      }
    });
    return "";
  }

  @EngineFunction("sendPush")
  public static String sendPush(@EngineFunctionParam("installationId") String installationId) {
    if (StringUtil.isEmpty(installationId)) {
      return "Illegal parameter: installationId is null.";
    }
    LCPush push = new LCPush();
    LCQuery query = LCInstallation.getQuery();
    query.whereEqualTo("installationId", installationId);
    push.setQuery(query);
    push.setMessage("test from LeanCloud Engine.");
    push.sendInBackground().subscribe(new Observer<JSONObject>() {
      @Override
      public void onSubscribe(@NotNull Disposable disposable) {

      }

      @Override
      public void onNext(@NotNull JSONObject jsonObject) {
        System.out.println("succeed to sent push with installationId: " + installationId);
      }

      @Override
      public void onError(@NotNull Throwable throwable) {
        System.out.println("failed to sent push with installationId: " + installationId);
        throwable.printStackTrace();
      }

      @Override
      public void onComplete() {

      }
    });
    return "";
  }
}
