package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;

public interface CommonDAO {

	/* ===================保存和批量保存========================== */
	<T> Object insert(T entity);

	<T> void insertBatch(List<T> list);
	
	/* ===================更新和批量更新========================== */
	<T> void update(T entity);

	<T> void updateBatch(List<T> list);
	
	/* ===================删除和批量删除========================== */
	<T> void delete(Class<T> entityClass, Object id);

	<T> void deleteBatch(Class<T> entityClass, List<Object> ids);
	
	void delete(Condition cond);
	
	/* ===================通过主键查询========================== */
	<T> T findById(Class<T> entityClass, Object id);

	/* ===================通过cond查询========================== */
	<T> T findOne(Condition cond);

	<T> List<T> findList(Condition cond);

	<T> Page<T> findPage(Condition cond);

	long count(Condition cond);

	/* ===================通过SQL查询和执行========================== */
	void insertBySql(String sql, Object... params);
	
	void deleteBySql(String sql, Object... params);
	
	void updateBySql(String sql, Object... params);

	Map<String, Object> findOneBySql(String sql, Object... params);

	List<Map<String, Object>> findListBySql(String sql, Object... params);

	Page<Map<String, Object>> findPageBySql(String sql, Object... params);
	
	<T> T findOneBySql(Class<T> entityClass, String sql, Object... params);
	
	<T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params);
	
	<T> Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params);
}