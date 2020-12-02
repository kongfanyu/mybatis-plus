package com.ujiuye.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ujiuye.mybatisplus.entity.User;
import com.ujiuye.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testLike(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","丽");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper1(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",29);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){
        System.out.println(userMapper.deleteById(1297156398435012609L));
    }

    @Test
    public void testSelectByPage(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println("当前页:"+page.getCurrent());
        System.out.println("总页数:"+page.getPages());
        System.out.println("记录数:"+page.getSize());
        System.out.println("总记录:"+page.getTotal());
        System.out.println("是否有下一页:"+page.hasNext());
        System.out.println("是否有上一页:"+page.hasPrevious());
    }


    @Test
    public void testSelectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","古力娜扎");
        map.put("age",29);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //测试乐观锁插件-失败
    @Test
    public void testOptimisticLockerFail(){
        User user = userMapper.selectById(1296833345184337921L);//查询数据
        user.setName("娜娜");//修改数据
        user.setEmail("luhan@qq.com");
        user.setVersion(user.getVersion() -1);
        userMapper.updateById(user);//执行更新
    }

    //测试乐观锁插件
    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(1296833345184337921L);//查询数据
        user.setName("鹿晗");//修改数据
        user.setEmail("luhan@qq.com");
        userMapper.updateById(user);//执行更新
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1296812844240109569L);
        user.setName("关晓彤");
        user.setEmail("xiaotong@163.com");

        int i = userMapper.updateById(user);
        System.out.println("更新结果:"+i);
    }

    @Test
    public void testIdWorker(){
        System.out.println(IdWorker.getId());
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三丰");
        user.setAge(22);
        user.setEmail("sanfeng@163.com");

        int insert = userMapper.insert(user);
        System.out.println("添加结果:"+insert);
    }


    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
