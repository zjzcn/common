package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;
import com.zebra.data.mapper.model.Condition;

public class MybatisDAO implements BaseDAO{

	private Mapper mapper;
	
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public <T> Object save(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void saveBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void updateBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Condition cond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void deleteBatch(Class<T> entityClass, List<Object> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Condition cond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T findById(Class<T> entityClass, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOne(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findList(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Page<T> findPage(Class<T> entityClass, Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Condition cond) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void executeSql(String sql, Object... params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> findOneBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findListBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> findPageBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOneBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
