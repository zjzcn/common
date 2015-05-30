package com.zebra.common.data.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	List<User> findListById(int id);
}
