package com.zebra.common.data.mybatis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebra.data.mapper.CommonDAO;
import com.zebra.data.mapper.Condition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DaoTest {
    
    @Autowired
    private CommonDAO baseDAO;
    
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
        List<User> users = baseDAO.findList(cond);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }
}
