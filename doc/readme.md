项目启动步骤

1：创建数据库mispdb，使用 mispdb.sql 中的 sql 语句创建数据库表和测试数据；

2: 修改 res/config.txt 文件，填入正确的数据库连接库名、用户名、密码；

3: 将项目导入 eclipse。推荐使用 Eclipse IDE for Java EE Developers

4: 打开 edu.mis.common包下的 MispConfig 文件，查看main方法中端口号,如8080。

5：右键单击Config并选择 Debug As ---> Java Application。

6: 打开浏览器输入  localhost:8080 即可查看运行效果

注意：tomcat下运行项目需要先删除 jetty-server-xxx.jar，否则会有冲突

本项目测试JRE环境JRE7,如导入项目报错，项目右键——build path——configure build path——libraries，修改为本机JRE版本环境。

