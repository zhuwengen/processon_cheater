# ProcessOn Cheater

ProcessOn 扩容作弊器

ProcessOn是一个很好用的在线作图工具。由于免费账户只有最大9个文件的容量，太小了，很容易超。
怎么办呢？自己动手，丰衣足食。

# 原理

利用ProcessOn成功邀请好友注册即增加3个文件容量的特性，来为自己的账户扩容。

1. 找到自己的邀请链接，账户中心---邀请链接。
1. 通过TempMail网站获得一个临时邮箱地址，将此次会话称为SessionA。
2. 通过邀请链接模拟访问ProcessOn网站(注意要保存cookie)，将此次会话称为SessionB。
3. 使用临时邮箱地址，随机一个密码和用户名，通过SessionB来模拟提交表单注册ProcessOn，此时ProcessOn会发送一封验证邮件到临时邮箱。
4. 通过SessionA刷新临时邮箱内的邮件，当邮件列表有邮件时，得到邮件的链接。
5. 通过SessionA访问邮件链接，匹配出ProcessOn的注册验证链接。
6. 访问注册验证链接，完成注册。

原理十分简单。

# 构建

项目使用java编码，gradle构建。因此需要java环境来构建和运行。

Linux 和 Mac
```bash
$ ./gradlew build
```

Windows
```cmd
gradlew.bat build
```

# 使用

```bash
java -jar processon_cheater.jar [你的邀请链接]
# 或
java -jar processon_cheater.jar [你的邀请链接] [线程池大小]
```

# 下载

[build/libs/processon_cheater.jar](build/libs/processon_cheater.jar)
