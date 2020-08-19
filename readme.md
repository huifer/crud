# crud template
- 增删改查模板项目,旨在减少重复代码. 
- 注意: **目前只支持redis-hash的数据类型** 


## 功能
- 单个表的数据库增删改查操作
- 单个表的数据库+缓存的增删改查操作
- 单个实体的缓存增删改查操作

## 如何使用
- 在启动类上添加如下代码
    - daoType 可选项有 mybatis 和 mybatis-plus
    - scanPackages 用来填写需要扫描的实体包路径,支持多个
```java
@EnableCrudTemplate(daoType = DaoType.MYBATIS_PLUS, scanPackages = {
    "com.github.huifer.mybatis.plus.mybatis"})
```

### mybatis 支持
- 添加依赖  

```xml
    <dependency>
      <groupId>com.github.huifer</groupId>
      <artifactId>for-mybatis</artifactId>
      <version>0.0.4-SNAPSHOT</version>
    </dependency>
```

- 对mapper进行修改
```java
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {}
```

- 对实体进行修改

```java
public class IssuesEntity implements BaseEntity {}
```


### mybatis plus 支持

- 添加依赖

```xml
    <dependency>
      <groupId>com.github.huifer</groupId>
      <artifactId>for-mybatis-plus</artifactId>
      <version>0.0.4-SNAPSHOT</version>
    </dependency>
```

- 对mapper进行修改

```java
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends BaseMapper<IssuesEntity>,
    AforMybatisPlus<Integer, IssuesEntity> 
```

- 对实体进行修改

```java
public class IssuesEntity implements BaseEntity {}
```


- 调用层 mybatis 和 mybatis-plus 都是同一个入口

```java
  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> crudFacade;
```
- 只需要引入`CrudFacade`组件就可以拥有数据库+缓存的crud操作了. 如果不需要缓存请将mapper上的`@CacheKey`删掉




### entity 支持

- 给实体对象添加注解 `@CacheKey`
    - redis-hash field 取值说明: idFiled=实体类的某个字段, idMethod=实体类中的一个方法,如果两者同时存在以`idMethod`为准, 建议填写一个
    
    
```java
@CacheKey(key = "tt", type = IssuesEntity.class, idFiled = "newTitle", idMethod = "ooo")
public class IssuesEntity  {


  private Integer id;
  private String newTitle;

  private String ooo() {
    return "OOO" + this.newTitle;
  }
}
```


## Contributers
- [@huifer](https://github.com/huifer)

## License
- Apache License 2.0