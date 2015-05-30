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
		for(T entity : list) {
			insert(entity);
		}
	}

	@Override
	public <T> void update(T entity) {
		mapper.update(entity);		
	}

	@Override
	public <T> void updateBatch(List<T> list) {
		for(T entity : list) {
			update(entity);
		}
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object id) {
		mapper.deleteById(entityClass, id);
	}

	@Override
	public <T> void deleteBatch(Class<T> entityClass, List<Object> ids) {
		for(Object id : ids) {
			delete(entityClass, id);
		}
	}

	@Override
	public void deleteByCond(Condition cond) {
		mapper.deleteByCond(cond);
	}

	@Override
	public <T> T findById(Class<T> entityClass, Object id) {
		Map<String, Object> result = mapper.findById(entityClass, id);
		return (T)EntityHelper.map2Bean(result, entityClass);
	}

	@Override
	public <T> T findOneByCond(Class<T> entityClass, Condition cond) {
		List<Map<String, Object>> result = mapper.findListByCond(cond);
		if(result==null || result.isEmpty()) {
			return null;
		}
		return (T)EntityHelper.map2Bean(result.get(0), entityClass);
	}

	@Override
	public <T> List<T> findListByCond(Class<T> entityClass, Condition cond) {
		List<Map<String, Object>> result = mapper.findListByCond(cond);
		return (List<T>)EntityHelper.maplist2BeanList(result, entityClass);
	}

	@Override
	public <T> Page<T> findPageByCond(Class<T> entityClass, Condition cond) {
		List<T> list = findListByCond(entityClass, cond);
		Page<T> page = new Page<T>(cond.getPageNo(), cond.getPageSize());
		page.setResultList(list);
		cond.page(-1, -1);
		long total = countByCond(cond);
		page.setTotalCount(total);
		return page;
	}

	@Override
	public long countByCond(Condition cond) {
		return mapper.countByCond(cond);
	}

	@Override
	public void insertBySql(String sql, Object... params) {
		mapper.insertBySql(sql, params);
	}

	@Override
	public void deleteBySql(String sql, Object... params) {
		mapper.deleteBySql(sql, params);
	}

	@Override
	public void updateBySql(String sql, Object... params) {
		mapper.updateBySql(sql, params);
	}

	@Override
	public Map<String, Object> findOneBySql(String sql, Object... params) {
		List<Map<String, Object>> result = mapper.findListBySql(sql, params);
		if(result==null || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<Map<String, Object>> findListBySql(String sql, Object... params) {
		return mapper.findListBySql(sql, params);
	}

	@Override
	public <T> T findOneBySql(Class<T> entityClass, String sql, Object... params) {
		List<Map<String, Object>> result = mapper.findListBySql(sql, params);
		if(result==null || result.isEmpty()) {
			return null;
		}
		return (T)EntityHelper.map2Bean(result.get(0), entityClass);
	}

	@Override
	public <T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params) {
		List<Map<String, Object>> result = mapper.findListBySql(sql, params);
		return (List<T>)EntityHelper.maplist2BeanList(result, entityClass);
	}

}
