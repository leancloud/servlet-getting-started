package cn.leancloud.demo.todo;

import cn.leancloud.EngineFunction;
import cn.leancloud.EngineFunctionParam;
import cn.leancloud.EngineRequestContext;
import cn.leancloud.sms.AVSMS;
import cn.leancloud.sms.AVSMSOption;
import cn.leancloud.types.AVNull;
import cn.leancloud.utils.StringUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    AVSMSOption option = new AVSMSOption();
    AVSMS.requestSMSCodeInBackground(mobile, option).subscribe(new Observer<AVNull>() {
      @Override
      public void onSubscribe(Disposable disposable) {

      }

      @Override
      public void onNext(AVNull avNull) {
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
}
