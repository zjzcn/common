package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;
import com.zebra.data.mapper.model.Condition;

public interface BaseDAO {

	/* ===================保存和批量保存========================== */
	<T> Object save(T obj);

	<T> void saveBatch(List<T> list);
	
	/* ===================更新和批量更新========================== */
	<T> void update(T obj);

	<T> void updateBatch(List<T> list);
	
	void update(Condition cond);

	/* ===================删除和批量删除========================== */
	<T> void delete(Class<T> entityClass, Object id);

	<T> void deleteBatch(Class<T> entityClass, List<Object> ids);
	
	void delete(Condition cond);
	
	/* ===================通过主键查询========================== */
	<T> T findById(Class<T> entityClass, Object id);

	/* ===================通过filter查询========================== */
	<T> T findOne(Class<T> entityClass, Condition cond);

	<T> List<T> findList(Class<T> entityClass, Condition cond);

	<T> Page<T> findPage(Class<T> entityClass, Condition cond);

	long count(Condition cond);

	/* ===================通过SQL查询和执行========================== */
	void executeSql(String sql, Object... params);

	Map<String, Object> findOneBySql(String sql, Object... params);

	List<Map<String, Object>> findListBySql(String sql, Object... params);

	Page<Map<String, Object>> findPageBySql(String sql, Object... params);
	
	<T> T findOneBySql(Class<T> entityClass, String sql, Object... params);
	
	<T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params);
	
	<T> Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params);
}