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
## 生成代码
```
@Autowired
GenerateOpt generateOpt;

@Test
public void test2() {
    generateOpt.create("user"); // 这里可以同时生成多个表的实例代码
}
```
### 然后导一下包就可以直接使用了
# 注意：由于update方法的条件不确定，所以如果要是用update，需要在mapper.xml中将<where>中不需要的条件参数删去
## 使用
```
@Autowired
UserService UserService;
    
@Test
public void test2() {
    userService.queryAll(new User());
    userService.queryOne(new User());
    userService.queryCount(new User());
    userService.saveAll(new ArrayList<>());
    userService.saveOne(new User());
    userService.modify(new User());
    userService.remove(new User());
}
    
```
