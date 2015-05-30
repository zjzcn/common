package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface CommonMapper {
	
	@InsertProvider(type = MapperProvider.class, method = "insert")
	<T> Object insert(T entity);

	@UpdateProvider(type = MapperProvider.class, method = "update")
	<T> void update(T entity);

	@DeleteProvider(type = MapperProvider.class, method = "deleteById")
	<T> void deleteById(@Param("entityClass")Class<?> entityClass, @Param("id")Object id);

	@DeleteProvider(type = MapperProvider.class, method = "deleteByCond")
	void deleteByCond(@Param("cond") Condition cond);
	
	@SelectProvider(type = MapperProvider.class, method = "findById")
	Map<String,Object> findById(@Param("entityClass")Class<?> entityClass, @Param("id")Object id);

	@SelectProvider(type = MapperProvider.class, method = "findListByCond")
	List<Map<String,Object>> findListByCond(@Param("cond")Condition cond);

	@SelectProvider(type = MapperProvider.class, method = "countByCond")
	Long countByCond(@Param("cond")Condition cond);
	
	@SelectProvider(type = MapperProvider.class, method = "findListBySql")
	List<Map<String, Object>> findListBySql(@Param("sql")String sql, @Param("sqlParams")Object... sqlParams);
	
	@InsertProvider(type = MapperProvider.class, method = "insertBySql")
	Object insertBySql(@Param("sql")String sql, @Param("sqlParams")Object... sqlParams);
	
	@UpdateProvider(type = MapperProvider.class, method = "updateBySql")
	void updateBySql(@Param("sql")String sql, @Param("sqlParams")Object... sqlParams);
	
	@DeleteProvider(type = MapperProvider.class, method = "deleteBySql")
	void deleteBySql(@Param("sql")String sql, @Param("sqlParams")Object... sqlParams);
}
