package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;

public class MybatisDAO implements CommonDAO{

	private CommonMapper mapper;
	
	public void setMapper(CommonMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public <T> Object insert(T entity) {
		return mapper.insert(entity);
	}

	@Override
	public <T> void insertBatch(List<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void updateBatch(List<T> list) {
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
		MapperParam param = new MapperParam(entityClass, id);
		Map<String, Object> result = mapper.findById(param);
		return (T)EntityHelper.map2Bean(result, entityClass);
	}

	@Override
	public <T> T findOne(Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findList(Condition cond) {
		MapperParam param = new MapperParam(cond);
		List<Map<String, Object>> result = mapper.findListByCond(param);
		
		return (List<T>)EntityHelper.maplist2BeanList(result, cond.getEntityClass());
	}

	@Override
	public <T> Page<T> findPage(Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Condition cond) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBySql(String sql, Object... params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBySql(String sql, Object... params) {
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
