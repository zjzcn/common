package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;
import com.zebra.data.mapper.model.Condition;



public class MapperProvider {

	public <T> Object save(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> void saveBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	public <T> void update(T obj) {
		// TODO Auto-generated method stub
		
	}

	public <T> void updateBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	public void update(Condition cond) {
		// TODO Auto-generated method stub
		
	}

	public <T> void delete(Class<T> entityClass, Object id) {
		// TODO Auto-generated method stub
		
	}

	public <T> void deleteBatch(Class<T> entityClass, List<Object> ids) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Condition cond) {
		// TODO Auto-generated method stub
		
	}

	public <T> T findById(Class<T> entityClass, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T findOne(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> findList(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Page<T> findPage(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count(Condition cond) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void executeSql(String sql, Object... params) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> findOneBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> findListBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Map<String, Object>> findPageBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T findOneBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
