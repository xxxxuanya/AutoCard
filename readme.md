首先需要手机放在打卡地点
其次,这个手机上的钉钉不能退出,电脑也不能关机,也就是如果不在就只能通过电脑上的钉钉发消息
对于钉钉不止用来记录考勤的亲不太友好,后面增加登录退出方法解决这个问题

项目打包就不说了,需要修改源码中 点击的位置和邮件信息,如果觉得不需要可以不设置邮箱服务器和密码等信息
这个需要配置的类在config包下面,手机开发者选项中,打开显示指针位置,可以看到点击位置的坐标
然后使用maven打包

使用方法：
1.手机打开调试模式,打开允许模拟点击,控制等(在开发者选项中)
2.电脑安装adb,直到执行 adb devices  可以 看到有手机的记录
    （adb可以自己百度,也可以用源码中的,复制到system32/windows/wowosys64文件夹）我忘了是不是叫wowsys64了 反正类似的
     复制好 在cmd输入adb应该就有反应了，不显示未找到应用balabala
3.安装java jdk或jre，推荐11/13/15/16这样的版本,这样的版本安装完后无需配置环境变量
4.新建一个文件夹 把源码打包出来的包放进去,然后在这个文件夹的地址栏,输入cmd并回车,cmd会自动定位到当前文件夹
5.然后输入 java -jar xxx.jar 运行即可
      会出现socket服务器连接失败的字样，忽略即可，然后按一次回车输入打卡时间
