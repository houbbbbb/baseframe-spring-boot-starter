# baseframe-spring-boot-starter
# 一个自动生成代码的支持mybatis的框架
## maven中央仓库依赖
```
<dependency>
    <groupId>com.github.houbbbbb</groupId>
    <artifactId>baseframe-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```
## 配置 application.yml
```
# 配置生成代码的数据库来源
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test
    userName: root
    password: root
# 配置生成路径
generate:
  enPath: G:/project/src/main/java/com/myproject/dto # 生成代码的包的绝对路径
  enPack: com.myproject.dto # 生成代码的包名
  enMPath: G:/project/src/main/resources/mapper # 生成mapper.xml的路径
```
