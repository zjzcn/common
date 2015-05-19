package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.zebra.data.Page;
import com.zebra.data.mapper.model.Condition;

public interface Mapper {
	/* ===================保存和批量保存========================== */
	@InsertProvider(method = "save", type = MapperProvider.class)
	<T> Object save(T obj);
	
	@InsertProvider(method = "saveBatch", type = MapperProvider.class)
	<T> void saveBatch(List<T> list);
	
	/* ===================更新和批量更新========================== */
	@UpdateProvider(method = "update", type = MapperProvider.class)
	<T> void update(T obj);
	
	@UpdateProvider(method = "updateBatch", type = MapperProvider.class)
	<T> void updateBatch(List<T> list);
	
	@UpdateProvider(method = "update", type = MapperProvider.class)
	void update(Condition cond);

	/* ===================删除和批量删除========================== */
	@DeleteProvider(method = "delete", type = MapperProvider.class)
	<T> void delete(Class<T> entityClass, Object id);

	@DeleteProvider(method = "deleteBatch", type = MapperProvider.class)
	<T> void deleteBatch(Class<T> entityClass, List<Object> ids);
	
	@DeleteProvider(method = "delete", type = MapperProvider.class)
	void delete(Condition cond);
	
	/* ===================通过主键查询========================== */
	@SelectProvider(method = "findById", type = MapperProvider.class)
	<T> T findById(Class<T> entityClass, Object id);

	/* ===================通过condition查询========================== */
	@SelectProvider(method = "findOne", type = MapperProvider.class)
	<T> T findOne(Class<T> entityClass, Condition cond);

	@SelectProvider(method = "findList", type = MapperProvider.class)
	<T> List<T> findList(Class<T> entityClass, Condition cond);

	@SelectProvider(method = "findPage", type = MapperProvider.class)
	<T> Page<T> findPage(Class<T> entityClass, Condition cond);

	@SelectProvider(method = "count", type = MapperProvider.class)
	long count(Condition cond);

	/* ===================通过SQL查询和执行========================== */
	@InsertProvider(method = "insertBySql", type = MapperProvider.class)
	void insertBySql(String sql, Object... params);
	
	@DeleteProvider(method = "deleteBySql", type = MapperProvider.class)
	void deleteBySql(String sql, Object... params);
	
	@UpdateProvider(method = "upadateBySql", type = MapperProvider.class)
	void upadateBySql(String sql, Object... params);

	@SelectProvider(method = "findOneBySql", type = MapperProvider.class)
	Map<String, Object> findOneBySql(String sql, Object... params);

	@SelectProvider(method = "findListBySql", type = MapperProvider.class)
	List<Map<String, Object>> findListBySql(String sql, Object... params);

	@SelectProvider(method = "findOneBySql", type = MapperProvider.class)
	Page<Map<String, Object>> findPageBySql(String sql, Object... params);
	
	@SelectProvider(method = "findOneBySql", type = MapperProvider.class)
	<T> T findOneBySql(Class<T> entityClass, String sql, Object... params);
	
	@SelectProvider(method = "findListBySql", type = MapperProvider.class)
	<T> List<T> findListBySql(Class<T> entityClass, String sql, Object... params);
	
	@SelectProvider(method = "findPageBySql", type = MapperProvider.class)
	<T> Page<T> findPageBySql(Class<T> entityClass, String sql, Object... params);
	
}