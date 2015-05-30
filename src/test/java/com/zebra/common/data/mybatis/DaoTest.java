package com.zebra.common.data.mybatis;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebra.data.Page;
import com.zebra.data.mapper.CommonDAO;
import com.zebra.data.mapper.Condition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DaoTest {
    
    @Autowired
    private CommonDAO baseDAO;
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testInsert(){
    	User user = new User();
    	user.setId(1);
    	user.setName("hello world");
        int id = (Integer)baseDAO.insert(user);
        System.out.println(id);
    }
    
    @Test
    public void testfind(){
        User user = baseDAO.findById(User.class, 1);
        Assert.assertNotNull(user);
    }

    @Test
    public void testfindList(){
    	Condition cond = Condition.from(User.class).between("id", 0, 2);
        List<User> users = baseDAO.findListByCond(User.class, cond);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }
    
    @Test
    public void testfindList3(){
    	Condition cond = Condition.from(User.class).between("id", 0, 2);
        User users = baseDAO.findOneByCond(User.class, cond);
        Assert.assertNotNull(users);
    }

    @Test
    public void testfindList4(){
    	Condition cond = Condition.from(User.class).between("id", 0, 2);
        long i = baseDAO.countByCond(cond);
        Assert.assertEquals(1, i);
    }
    
    @Test
    public void testfindList7(){
    	Condition cond = Condition.from(User.class).between("id", 0, 3).page(0, 10);
        Page<User> page = baseDAO.findPageByCond(User.class, cond);
        Assert.assertNotNull(page);
        Assert.assertEquals(2, page.getTotalCount());
    }
    
    @Test
    public void testfindList1(){
        List<User> users = userDao.findListById(2);
        User user = users.get(0);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }
    
    @Test
    public void testfindList2(){
		String sql = "select * from tb_user where id=?";
        List<Map<String, Object>> users = baseDAO.findListBySql(sql, 1);
        Map<String, Object> user = users.get(0);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }
    
    @Test
    public void testfindList5(){
		String sql = "insert tb_user(id,name) values(?, ?)";
        baseDAO.insertBySql(sql, 2, "hehe");
    }
    
    @Test
    public void testfindList6(){
		String sql = "update tb_user set name=? where id=?";
        baseDAO.updateBySql(sql, "hehe test", 2);
    }
}
