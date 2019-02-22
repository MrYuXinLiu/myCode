package com.example.mybatis_plus;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis_plus.entity.User;
import com.example.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author: lxy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    /**
     * Mybatis - plus 所有的Api 今天我们来把所有的测试下
     */

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入方法,这里Id自增策略采用Id_worker
     */
    @Test
    public void insert() {

        User user = new User();
        user.setAge(22);
        user.setEmail("110@qq.com");
        user.setName("loser");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /**
     * 根据ID进行修改
     * int updateById(@Param("et") T var1);
     */
    @Test
    public void updateById() {

        User user = userMapper.selectById(1098915188356177921L);
        User user2 = userMapper.selectById(1098915188356177921L);


        user2.setName("屌丝");
        userMapper.updateById(user2);
        userMapper.updateById(user);
    }


    /**
     * T selectById(Serializable var1);
     */
    @Test
    public void selectById() {
        System.out.println(userMapper.selectById(1098868303713103874L));
    }

    /**
     * List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> var1);
     */

    @Test
    public void selectBatchIds() {
        userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5)).forEach(System.out::print);
    }

    /**
     * List<T> selectByMap(@Param("cm") Map<String, Object> var1);
     */
    @Test
    public void selectByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "loser");
        map.put("age", 99);
        userMapper.selectByMap(map).forEach(System.out::println);
    }

    /**
     * IPage<T> selectPage(IPage<T> var1, @Param("ew") Wrapper<T> var2);
     */
    @Test
    public void selectPage() {

        IPage<User> userIPage = userMapper.selectPage(new Page<User>(2, 5), null);
        //System.out.println(userIPage); // 所有数据
        System.out.println(userIPage.getCurrent());//获取当前页码
        System.out.println(userIPage.getPages());//获取最大页码数
        userIPage.getRecords().forEach(System.out::println);//获取遍历当前页的所有参数
        System.out.println(userIPage.getTotal());

    }

    /**
     * IPage<Map<String, Object>> selectMapsPage(IPage<T> var1, @Param("ew") Wrapper<T> var2);
     */
    @Test
    public void selectMapsPage() {


        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(new Page<User>(1, 5), null);
        mapIPage.getRecords().forEach(System.out::println);
    }

    /**
     * int deleteById(Serializable var1);
     */
    @Test
    public void deleteById() {
        userMapper.deleteById(1098886935704825857L);
    }

    /**
     * int deleteByMap(@Param("cm") Map<String, Object> var1);
     */
    @Test
    public void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "屌丝");
        map.put("age", 99);
        userMapper.deleteByMap(map);
    }

    /**
     * int deleteBatchIds(@Param("coll") Collection<? extends Serializable> var1);
     */
    @Test
    public void deleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1098868220347076609L, 1098884497807544322L));
    }

    @Test
    public void deleted() {
        System.out.println(userMapper.deleteById(5L));
    }

    /**
     * List<T> selectList(@Param("ew") Wrapper<T> var1);
     */
    @Test
    public void selectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * 以下是带条件的CRUD
     */

    /**
     * 这个可以进行修改Id 第一个参数是 实体对象,第二个是条件参数
     * <p>
     * <p>
     * <p>
     * Wrapper ： 条件构造抽象类，最顶端父类
     * AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
     * QueryWrapper ： Entity 对象封装操作类，不是用lambda语法
     * UpdateWrapper ： Update 条件封`装，用于Entity对象更新操作
     * AbstractLambdaWrapper ： Lambda 语法使用 Wrapper统一处理解析 lambda 获取 column。
     * LambdaQueryWrapper ：看名称也能明白就是用于Lambda语法使用的查询Wrapper
     * LambdaUpdateWrapper ： Lambda 更新封装Wrapper
     * <p>
     * <p>
     * ge、gt、le、lt、isNull、isNotNull
     * <p>
     * GE : 大于等于
     * gt : 大于
     * le : 小于等于
     * lt : 小于
     * isNull : 是空
     * isNotNull
     * <p>
     * int update(@Param("et") T var1, @Param("ew") Wrapper<T> var2);
     */
    @Test
    public void xupdate() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("id").ge("age", 22);
        userMapper.update(new User(null, "luser", 18, "120@qq.com", null, null, null, null), queryWrapper);

    }

    /**
     * T selectOne(@Param("ew") Wrapper<T> var1);
     * <p>
     * eq : 等于
     * nq : 不等于
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1098915274842730497L);
        System.out.println(userMapper.selectOne(queryWrapper));

    }

    /**
     * Integer selectCount(@Param("ew") Wrapper<T> var1);
     * <p>
     * between : 闭区间 哪到哪   1<=age<= 18;
     * notBetween : 闭区间 不到哪到哪    1>=age>=18;
     */
    @Test
    public void selectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notBetween("age", 1, 18);
        System.out.println(userMapper.selectCount(queryWrapper));
    }

    /**
     * int delete(@Param("ew") Wrapper<T> var1);
     * allEq  匹配所有才删除
     */
    @Test
    public void delete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 0L);
        map.put("name", "loser");
        queryWrapper.allEq(map);
        userMapper.delete(queryWrapper);
    }


    /**
     * List<Map<String, Object>> selectMaps(@Param("ew") Wrapper<T> var1);
     * <p>
     * like  模糊查询 %xxx%
     * notLike 不模糊查询 全值匹配
     * likeLift 模糊查询,右边全值匹配 %xxx
     * likeRight 模糊查询,左边全值匹配 xxx%
     */
    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> like = queryWrapper.likeRight("name", "lus");

        userMapper.selectMaps(like).forEach(System.out::println);
    }

    /**
     * List<Object> selectObjs(@Param("ew") Wrapper<T> var1);
     * <p>
     * in、notIn、inSql、notinSql、exists、notExists
     * <p>
     * in  在什么里面
     * notIn 不在这里边
     * inSql 相当于多表查询,自己编写sql 多用于子查询 queryWrapper.inSql("id", "id<3");  select id from user where id < 3
     * notinSql 相当于多表查询,自己编写sql 多用于子查询 queryWrapper.inSql("id", "id>=3");  select id from user where id < 3
     * exists 多表查询,当数据库有满足自己写的sql时相当如 selectAll
     * notExists 多表查询,当数据库有满足自己写的sql时相当如 不查, 不满足则查所有
     */
    @Test
    public void selectObjs() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.notIn("id",1098915274842730497L,1098915114263801857L);
        queryWrapper.notExists(true, "select id from user where id = 1");

        userMapper.selectObjs(queryWrapper).forEach(System.out::println);
    }

    /**
     * or、and
     * <p>
     * <p>
     * or : 或者
     * and : 必须
     * UpdateWrapper ： Update 条件封装，用于Entity对象更新操作
     */
    @Test
    public void andOr() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.likeRight("name", "ll").or().gt("age", 18);

        userMapper.selectList(userUpdateWrapper).forEach(System.out::println);
    }

    /**
     * 嵌套Or and
     * lambda   i 是自定的一个参数,随便用,然后搭配其他的条件就OK
     */
    @Test
    public void orAnd() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.gt("age", 18).or(i -> i.eq("name", "luser").ne("name", "luser"));

        userMapper.selectList(userUpdateWrapper).forEach(System.out::println);
    }

    /**
     * orderBy、orderByDesc、orderByAsc
     *
     * Desc 倒叙列出查询的
     * ASC  正序列出查询的
     *
     *
     */
    @Test
    public void orderBy() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

        userUpdateWrapper.orderBy(true,false,"id");
        userMapper.selectList(userUpdateWrapper).forEach(System.out::println);
    }
    /**
     *
     *last
     直接拼接到 sql 的最后
     注意：只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
     *
     * last 就是在原sql 语句中添加sql语句,记住只能添加一条,并且是不安全的
     */
    @Test
    public void last(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1,2");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    /**
     * 指定要查询的列 select
     *
     * 只有查询出来的值,其余为null , 这时候可以用beanUtils  去复制一下
     */

    @Test
    public void select(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name","age");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }
    /**
     * set、setSql
     *
     *
     * set 相当于在修改的值里面自定义值  = user.setAge();
     *
     * setSql 就是写一个set的语句 = email = '120@qq.com'
     *
     * sql :  UPDATE user SET name=?, age=?, email=?, create_time=?, update_time=?, version=?,
     * `email = '120@qq.com'` WHERE deleted=0 AND version = ? AND id = 1
     */
    @Test
    public void set(){

        User user = userMapper.selectById(1L);
        user.setName("屌丝");

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.setSql("email = '120@qq.com' ").last("AND id = 1");
        userMapper.update(user,userUpdateWrapper);

        userMapper.selectList(null).forEach(System.out::println);
    }
}
