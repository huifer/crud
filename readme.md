# crud template
- 增删改查在业务系统中是一个高频操作,切方法逻辑也可能几近相同. 本项目仅对单表增删改查的业务做一个统一的处理.
- 注意: **目前只支持redis-hash的数据类型** 


## 为什么使用. 
以一个用户的缓存数据作为例子.

- 新增: 伪代码

```java
// 插入数据库
mapper.insert(user)
// 插入缓存
redis.insert(user)
```

- 修改

```
// 删除缓存
redis.del(user.id())
// 更新数据库
mapper.update(user)
// 插入缓存
redis.insert(user)
```

- 查询byId

```
User user = null
// 查询缓存
user = redis.byId(id)
if (user ==null) {
    // 查询数据库
    user = mapper.byId(id)
}
return user
```
- 一个用户的实体操作如果带有缓存操作基本如上所属, 那再来几个其他需要缓存操作的呢.这部分代码会反复重写. 


## 如何使用

- 添加依赖

```xml
<dependency>
  <groupId>com.github.huifer</groupId>
  <artifactId>template</artifactId>
  <version>0.0.2-Releases</version>
</dependency>
```

- 在启动类上添加`@EnableCrudTemplate`

```java
@SpringBootApplication
@EnableCrudTemplate
public class WebDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebDemoApplication.class, args);
  }
}
```

- 在实体类上增加接口`BaseEntity`.
    - 通常我们使用`id`这个名字作为数据主键(唯一键),因此在这里我直接定义了`getId()`这样的接口避免重写方法
    
    
- 对mapper进行改造
    1. 继承接口`A` 
    2. 是否使用缓存. 如果是则添加`@CacheKey`, 其中 key 是缓存的key,type是数据类型
    
```java
@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {
  int insertSelective(IssuesEntity record);
}
```

- 测试用例: `com.example.webdemo.WebDemoApplicationTests`



- 如果需要接入 Mybatis-Plus 将注解修改成乳香形式

```
@EnableCrudTemplate(daoType = DaoType.MYBATIS_PLUS)
```

- 并且注意, `A`接口一定要更在 `BaseMapper` 接口后面

```java
@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends BaseMapper<IssuesEntity>, A<Integer, IssuesEntity> {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);
}

```