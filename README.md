# Cloud Function Getting Started

运行在 [LeanCloud](https://leancloud.cn/) 云引擎 Java 环境上的云函数项目示例。

## 本地运行

首先确认本机已经安装 [LeanCloud 命令行工具](https://www.leancloud.cn/docs/leanengine_cli.html)，然后执行下列指令：

```
$ git clone -b cloud-function https://github.com/leancloud/java-war-getting-started.git cloud-function-getting-started
$ cd cloud-function-getting-started
```

安装依赖：

```
mvn package
```

登录账户：
```
lean login
```

关联项目：
```
lean checkout
```
根据列表提示，输入数字，依次按回车确认，以关联到你的应用。


启动项目：

```
$(lean up)
java -jar target/cloud-function-getting-started-0.0.1-SNAPSHOT.jar
```

项目启动后会监听本机 3000 端口。

## 本机调试

再启动一个终端，使用命令行工具启动云函数调试页面：

```
lean debug
```

即可打开 [localhost:3001](http://localhost:3001) 测试请求本机云函数和 Hook 函数。

## 部署到 LeanEngine

部署到预备环境（若无预备环境则直接部署到生产环境）：
```
lean deploy
```

将预备环境的代码发布到生产环境：
```
lean publish
```

## 相关文档

* [云引擎服务总览](https://leancloud.cn/docs/leanengine_overview.html)
* [网站托管开发指南](https://leancloud.cn/docs/leanengine_webhosting_guide-java.html)
* [云函数开发指南](https://leancloud.cn/docs/leanengine_cloudfunction_guide-java.html)
* [数据存储开发指南](https://leancloud.cn/docs/leanstorage_guide-java.html)
* [命令行工具使用详解](https://leancloud.cn/docs/leanengine_cli.html)
