# Java Getting started

一个简单的使用 Servlet 的 Java 服务端应用，打成 war 包运行。
可以运行在 LeanEngine Java 运行时环境。

## 一键部署
[![Deploy to LeanEngine](http://ac-32vx10b9.clouddn.com/109bd02ee9f5875a.png)](https://leancloud.cn/1.1/functions/_ops/deploy-button)

## 本地运行

首先确认本机已经安装 [LeanCloud 命令行工具](https://www.leancloud.cn/docs/leanengine_cli.html)，然后执行下列指令：

```
$ git clone https://github.com/leancloud/java-war-getting-started.git
$ cd java-war-getting-started
```

安装依赖：

```
mvn package
```

关联应用：

```
lean app add origin <appId>
```

这里的 appId 填上你在 LeanCloud 上创建的某一应用的 appId 即可。origin 则有点像 Git 里的 remote 名称。

启动项目：

```
lean up
```

应用即可启动运行：[localhost:3000](http://localhost:3000)

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
