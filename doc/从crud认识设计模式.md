# 从 crud 认识设计模式
- 在业务系统中增删改查(crud)是经常需要开发的内容,本文主要从增删改查来对设计模式进行一个学习或使用.
- 一般我们对一个表的增删改查有如下一些接口定义

```java
boolean insert(Object o);
Object byId(Integer id);
boolean del(Integer id);
boolean editor(Integer interfaces, Object o;
```

- 再带上 redis 的 crud 操作,具体不进行列举.

## 整体操作
- 在这里我们仅针对单表而言
### C-创建
- 通常我们的创建流程是这样子的.
    1. 接收请求,将请求转换为db对象,持久化
    2. 如果需要制作缓存,则放入缓存中

- 伪代码如下
```java
boolean b =  mapper.save(s)
if (b){
  redis.save(s)
}
```
    
### R-读取
- 此处以byId为例. where 条件不易放入 redis 作为 key ,如果作为 key 数据量会过多
- 通常操作
    1. 尝试从 redis 中读取
    2. 读取到了则返回,没有读取到通过db进行查询
    
- 伪代码
```java
Object o =null
o = redis.read(id)
if (o ==null) {
  o = mapper.read(id)
}
```


### U-更新
- 通常操作
    1. 删除 redis 的记录(避免读到假数据)
    2. 更新db
    3. 放入 redis

- 伪代码
```java
redis.del()
mapper.update(o)
redis.save(o)
```
### D-删除
- 通常操作
    1. redis 删除
    2. db 删除
- 伪代码
```java
redis.del()
mapper.del(o)
```


## 设计改良
- 上述的操作大部分内容相似度很高, 在日常编写的时候我们可能会经常写这样子的代码.代码量过多, 以及代码复用率不是那么好.因此对其做出改良.

- 抽象数据库操作和缓存操作. 其操作内容就是 CRUD 几个基本操作. 再次强调:**单表操作**
- 在抽象 crud 基本操作前需要在提出一个问题. 
    - 我们的主键可能是多种类型的. 常见有 int 、 varchar 这两种, 如何让我们的 crud 接口更好的使用？
    - 下面是笔者提供的解决方案.
    
    
#### id 多类型的解决
- 首先定义一个空接口 `IdInterface` 它用来标识这是一个 id 接口

```java
public interface IdInterface {

}
```

- 根据常用 int 和 varchar 设计出如下两个子接口. 他们的作用会在后续得到进一步的体现. 当前阶段仅需要知道他们是 id 接口的继承即可.
    - 方法 `id()` 用来返回具体的 id 值

```java
public interface IntIdInterface extends IdInterface {
  int id();
}

public interface StrIdInterface extends IdInterface {
  String id();
}
```

#### CRUD 基础接口抽象

- 首先定义 db 操作接口

```java
public interface DbOperation<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I interfaces);

  boolean del(I interfaces);

  boolean editor(I interfaces, T t);

}

```
- 代码解释, 此处使用泛型 `T` 来标识接受的类型, I 来标识接收的接口, 这里存在2中情况. 第一种 I = IntIdInterface , 第二种 I = StrIdInterface

- 继续定义 redis 的操作

```java
public interface RedisOperation<T, I extends IdInterface> {

  void insert(T t);

  void update(I i, T t);

  void del(I i);

  T byId(I i);

}

```


- redis 缓存的数据结构选择. 这里为了方便使用 id 进行查询选择 hash 进行开发. 如有需求可自定义选择数据结构.
- redis 操作 hash 的行为也可以抽象出来.这里就不使用接口了. 使用一个父类来进行解决.

```java
public abstract class RedisHashKeyOperation<T> {

  Gson gson = new Gson();
  @Autowired
  private StringRedisTemplate redisTemplate;

  protected void update(String key, String id, T t) {

    T redisObj = this.byId(key, id, t.getClass());
    if (!Objects.isNull(redisObj)) {

      // 如果是redis中的类型和当前传入的类型相同
      if (redisObj.getClass().equals(t.getClass())) {
        this.insert(key, id, t);
      }
    }
  }

  protected void insert(String key, String id, T t) {
    redisTemplate.opsForHash().put(key, id, gson.toJson(t));
  }

  protected T byId(String key, String id, Class<?> clazz) {
    String o = (String) redisTemplate.opsForHash().get(key, id);
    return (T) gson.fromJson(o, clazz);
  }

  protected void delete(String key, String id) {
    this.redisTemplate.opsForHash().delete(key, id);
  }
}
```

- 一般的对 redis hash 操作基本就是这样子的. 不会有过多的修改.

- 创建两个表来使用以下. 实体对象 mapper 不在此进行展开代码

```sql
CREATE TABLE `project_int` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `project_str` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
```

- 贴出一个int为主键的操作
```java
@Service("ProjectIntDbOperationImpl")
public class ProjectIntDbOperationImpl implements DbOperation<ProjectInt, IntIdInterface> {

  @Autowired
  private ProjectIntMapper projectIntMapper;

  @Override
  public boolean insert(ProjectInt projectInt) {
    return projectIntMapper.insert(projectInt) > 0;
  }

  @Override
  public ProjectInt byId(IntIdInterface interfaces) {
    return projectIntMapper.selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(IntIdInterface interfaces) {
    return projectIntMapper.deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(IntIdInterface interfaces, ProjectInt projectInt) {
    // 更新存在策略
    ProjectInt projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.projectIntMapper.updateByPrimaryKey(projectInt1) > 0;
  }

}



@Service("ProjectIntRedisOperationImpl")
public class ProjectIntRedisOperationImpl
    extends RedisHashKeyOperation<ProjectInt>
    implements
    RedisOperation<ProjectInt, IntIdInterface> {

  public static final String CACHE_PROJECT_INT = "cache:project_int";

  public void insert(ProjectInt projectInt) {
    super.insert(CACHE_PROJECT_INT, String.valueOf(projectInt.getId()), projectInt);
  }

  public void update(IntIdInterface strIdInterface, ProjectInt projectInt) {
    super.update(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()), projectInt);
  }

  public void del(IntIdInterface strIdInterface) {
    super.delete(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()));
  }

  public ProjectInt byId(IntIdInterface intIdInterface) {
    return super.byId(CACHE_PROJECT_INT, String.valueOf(intIdInterface.id()), ProjectInt.class);
  }

}

```

- varchar 作为主键

```java
@Service("ProjectStrDbOperationImpl")
public class ProjectStrDbOperationImpl implements DbOperation<ProjectStr, StrIdInterface> {

  @Autowired
  private ProjectStrMapper projectStrMapper;

  @Override
  public boolean insert(ProjectStr projectInt) {
    return projectStrMapper.insert(projectInt) > 0;
  }

  @Override
  public ProjectStr byId(StrIdInterface interfaces) {
    return projectStrMapper.selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(StrIdInterface interfaces) {
    return projectStrMapper.deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(StrIdInterface interfaces, ProjectStr projectInt) {
    // 更新存在策略
    ProjectStr projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.projectStrMapper.updateByPrimaryKey(projectInt1) > 0;
  }
}


@Service("ProjectStrRedisOperationImpl")
public class ProjectStrRedisOperationImpl
    extends RedisHashKeyOperation<ProjectStr>
    implements
    RedisOperation<ProjectStr, StrIdInterface> {

  public static final String CACHE_PROJECT_INT = "cache:project_int";

  public void insert(ProjectStr projectStr) {
    super.insert(CACHE_PROJECT_INT, String.valueOf(projectStr.getId()), projectStr);
  }

  public void update(StrIdInterface strIdInterface, ProjectStr projectStr) {
    super.update(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()), projectStr);
  }

  public void del(StrIdInterface strIdInterface) {
    super.delete(CACHE_PROJECT_INT, String.valueOf(strIdInterface.id()));
  }

  public ProjectStr byId(StrIdInterface intIdInterface) {
    return super.byId(CACHE_PROJECT_INT, String.valueOf(intIdInterface.id()), ProjectStr.class);

  }

}

```

- 基本操作已经具备. 但是对外的方法还需要再一次进行抽象. 在整体操作中,CRUD的逻辑类似. 继续进行改进.
- 统一对外提供的接口如下

```java
public interface ByIdOperationFacade<T, I extends IdInterface> {

  boolean insert(T t);

  T byId(I i);

  boolean del(I i);

  boolean editor(I i, T t);

  DbOperation getDbOperation();

}

```

- 光有这个还不够. 我们提供的统一 crud 方法还需要得到两个关键对象, 1. DbOperation 2. RedisOperation. 定义出操作集合类

```java
public class OperationCollections {

  private DbOperation dbOperation;

  private RedisOperation redisOperation;
}
```

- 有了操作集合. 缺少一个生成它的工具
```java
public interface ByIdOperationFactory {

  OperationCollections factory(Class<?> clazz);
  
}
```
- 实现如下. 注意:**此处为了让泛型更好的使用, 这里采用的是类型对应一个操作对象(DbOperation,RedisOperation).**
    - 需要改进的地方: 给`DbOperation` 和 `RedisOperation` 添加一个函数用来获取类型
```java
@Service("ByIdOperationFactoryImpl")
public class ByIdOperationFactoryImpl implements ByIdOperationFactory {

  static Map<Class, DbOperation> dbOperationMap = new HashMap<>();
  static Map<Class, RedisOperation> redisOperationHashMap = new HashMap<>();
  @Autowired
  private ApplicationContext context;

  @PostConstruct
  public void init() {
    Map<String, DbOperation> beansOfType = context.getBeansOfType(DbOperation.class);
    beansOfType.forEach(
        (k, v) -> {
          Class type = v.type();
          dbOperationMap.put(type, v);
        }
    );

    Map<String, RedisOperation> beansOfType1 = context.getBeansOfType(RedisOperation.class);
    beansOfType1.forEach((k, v) -> {
      Class type = v.type();
      redisOperationHashMap.put(type, v);
    });

  }

  @Override
  public OperationCollections factory(Class<?> clazz) {
    OperationCollections operationCollections = new OperationCollections();
    DbOperation dbOperation = dbOperationMap.get(clazz);
    RedisOperation redisOperation = redisOperationHashMap.get(clazz);

    operationCollections.setDbOperation(dbOperation);
    operationCollections.setRedisOperation(redisOperation);

    return operationCollections;
  }
}

```

- 实现一个通用的 id 操作对象

```java
public abstract class CommonByIdOperation<T, I extends IdInterface>
    implements ByIdOperationFacade<T, I> {

  @Autowired
  @Qualifier("ByIdOperationFactoryImpl")
  ByIdOperationFactory byIdOperationFactory;

  @Override
  public boolean insert(T t) {
    throw new RuntimeException("没有实现");
  }

  @Override
  public DbOperation getDbOperation() {
    throw new RuntimeException("没有实现");
  }

  public boolean editor(I i, T t) {

    boolean editor = false;

    OperationCollections operationCollections = this.operationCollection();
    RedisOperation redisOperation = operationCollections.getRedisOperation();

    if (redisOperation != null) {
      redisOperation.del(i);
    }

    DbOperation dbOperation = operationCollections.getDbOperation();

    if (dbOperation != null) {
      editor = dbOperation.editor(i, t);
    }

    if (redisOperation != null) {
      redisOperation.insert(t);
    }

    return editor;
  }


  public boolean del(I i) {
    boolean del = false;

    OperationCollections operationCollections = this.operationCollection();

    RedisOperation redisOperation = operationCollections.getRedisOperation();
    if (redisOperation != null) {
      redisOperation.del(i);
    }

    DbOperation dbOperation = operationCollections.getDbOperation();
    if (dbOperation != null) {
      del = dbOperation.del(i);
    }

    return del;
  }

  public T byId(I i) {
    T result = null;
    RedisOperation redisOperation = this.operationCollection().getRedisOperation();
    if (redisOperation != null) {
      result = (T) redisOperation.byId(i);
    }

    DbOperation dbOperation = this.operationCollection().getDbOperation();

    if (dbOperation != null) {
      if (result == null) {
        System.out.println("从数据库获取");
        result = (T) dbOperation.byId(i);
      }
    }

    return result;
  }


  public OperationCollections operationCollection() {
    return byIdOperationFactory.factory(clazz());
  }

  protected Class<?> clazz() {
    throw new IllegalArgumentException("类型异常");
  }
}
```
- 说明: 这里的 insert 操作不建议在此进行处理. 应为id是在mybatis插入后才会有. 当然也可以通过一个bean—copy的方式在这里获取. 但是我们的命名如果不同. beanCopy 就不行了. 
- 简单概述bean-copy的方式
```java
public class BeanCopy {

  public static void main(String[] args) {
    StrId strId = new StrId();
    strId.setId("str_id");

    CpId cpId = new CpId();
    BeanUtils.copyProperties(strId, cpId);
    System.out.println(cpId.getId());
  }
  @Data
  static class CpId {
    private Object id;
  }

  @Data
  static class StrId {

    private String id;
  }
}

```


- 最后的组装过程

```java
@Service("ProjectIntDbOperationImpl")
public class ProjectIntDbOperationImpl implements DbOperation<ProjectInt, IntIdInterface> {

  @Autowired
  private ProjectIntMapper projectIntMapper;

  @Override
  public boolean insert(ProjectInt projectInt) {
    return projectIntMapper.insert(projectInt) > 0;
  }

  @Override
  public ProjectInt byId(IntIdInterface interfaces) {
    return projectIntMapper.selectByPrimaryKey(interfaces.id());
  }

  @Override
  public boolean del(IntIdInterface interfaces) {
    return projectIntMapper.deleteByPrimaryKey(interfaces.id()) > 0;
  }

  @Override
  public boolean editor(IntIdInterface interfaces, ProjectInt projectInt) {
    // 更新存在策略
    ProjectInt projectInt1 = this.byId(interfaces);
    projectInt1.setName(projectInt.getName());

    return this.projectIntMapper.updateByPrimaryKey(projectInt1) > 0;
  }

  @Override
  public Class<?> type() {
    return ProjectInt.class;
  }
}
```


```java
@Service("ProjectStrFacade")
public class ProjectStrFacade extends CommonByIdOperation<ProjectStr, StrIdInterface> implements
    ByIdOperationFacade<ProjectStr, StrIdInterface> {

  @Override
  public boolean insert(ProjectStr projectInt) {
    DbOperation dbOperation = this.getDbOperation();
    boolean insert = false;
    if (dbOperation != null) {
      insert = dbOperation.insert(projectInt);
    }
    RedisOperation redisOperation = this.operationCollection().getRedisOperation();
    if (redisOperation != null) {
      redisOperation.insert(projectInt);
    }
    return insert;
  }

  @Override
  public ProjectStr byId(StrIdInterface strIdInterface) {
    return super.byId(strIdInterface);
  }

  @Override
  public boolean del(StrIdInterface strIdInterface) {
    return super.del(strIdInterface);
  }

  public boolean editor(StrIdInterface strIdInterface, ProjectStr projectInt) {
    return super.editor(strIdInterface, projectInt);
  }

  @Override
  public DbOperation getDbOperation() {
    return this.operationCollection().getDbOperation();
  }

  @Override
  protected Class<?> clazz() {
    return ProjectStr.class;
  }
}
```

## 测试

```java
@SpringBootTest
class DemoApplicationTests {

  Gson gson = new Gson();
  @Autowired
  private ProjectStrMapper projectStrMapper;
  @Autowired
  @Qualifier("projectIntFacade")
  private ByIdOperationFacade<ProjectInt, IntIdInterface> byIdOperationFacade;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  void testInsert() {
    ProjectInt projectInt = new ProjectInt();
    projectInt.setName("JJJ");
    this.byIdOperationFacade.insert(projectInt);
  }

  @Test
  void testUpdate() {
    ProjectInt projectInt = this.byIdOperationFacade.byId(new IntIdInterface() {
      @Override
      public int id() {
        return 1;
      }
    });

    projectInt.setName("update");
    this.byIdOperationFacade.editor(new IntIdInterface() {
      @Override
      public int id() {
        return projectInt.getId();
      }
    }, projectInt);
  }

  @Test
  void testDel() {
    this.byIdOperationFacade.del(new IntIdInterface() {
      @Override
      public int id() {
        return 1;
      }
    });
  }

  @Test
  void testById() {
    ProjectInt projectInt = this.byIdOperationFacade.byId(new IntIdInterface() {
      @Override
      public int id() {
        return 1;
      }
    });
    System.out.println();
  }
}
```

- 我们对外暴露的类只有ByIdOperationFacade.  

```java
  @Autowired
  @Qualifier("projectIntFacade")
  private ByIdOperationFacade<ProjectInt, IntIdInterface> byIdOperationFacade;
```

- 通过不同的实现来操作不同的数据库对象