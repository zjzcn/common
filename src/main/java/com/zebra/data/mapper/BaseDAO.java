package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import com.zebra.data.Page;

public interface BaseDAO<T> {

	/* ===================保存和批量保存========================== */
	Object save(T obj);

	void saveBatch(List<T> list);
	
	/* ===================更新和批量更新========================== */
	void update(T obj);

	void updateBatch(List<T> list);

	/* ===================删除和批量删除========================== */
	void deleteById(Class<T> entityClass, Object id);

	void deleteBatchById(Class<T> entityClass, List<Object> list);
	
	void delete(T obj);
	
	void deleteBatch(List<T> list);

	void delete(Select select);
	
	/* ===================通过主键查询========================== */
	T findById(Class<T> entityClass, Object id);

	/* ===================通过filter查询========================== */
	T findOne(Class<T> entityClass, Select select);

	List<T> findList(Class<T> entityClass, Select select);

	Page<T> findPage(Class<T> entityClass, Select select);

	long count(Select select);

	/* ===================通过SQL查询和执行========================== */
	void executeSql(String sql, Object... params);

	Map<String, Object> findOneBySql(String sql, Object... params);

	List<Map<String, Object>> findListBySql(String sql, Object... params);

	Page<Map<String, Object>> findPageBySql(String sql, Object... params);
	
	T findOneBySql(Class<T> entityClass, String sql, Object... params);
	
	List<T> findListBySql(Class<T> entityClass, String sql, Object... params);
	
	Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params);
}