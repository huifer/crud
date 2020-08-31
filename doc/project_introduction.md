# Crud 项目介绍
> 简化单表的CRUD基本代码.
>
## 为什么使用
- 比如学生管理系统.表设计有 课程表`t_classes`等等...在管理系统中我们需要添加`课程`的时候需要做一次 controller 、 service 、 redis 、 dao 这几类操作. 每多一个表格都需要做这一批操作.
    一般有新增、修改、删除、根据 id 查询. 
    
- 当使用了这个项目后通过标记一些注解即可获得上述的功能.




## 特性
- mybatis + redis 增删改查操作
- mybatis 增删改查操作
- redis 增删改查操作
- 忽略 controller 编辑提供 servlet 的增删改查操作



