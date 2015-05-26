package com.zebra.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface CommonMapper {
	
	@InsertProvider(type = MapperProvider.class, method = "insert")
	<T> Object insert(T entity);

	@UpdateProvider(type = MapperProvider.class, method = "update")
	<T> void update(T entity);

	@DeleteProvider(type = MapperProvider.class, method = "deleteById")
	<T> void deleteById(MapperParam param);

	@DeleteProvider(type = MapperProvider.class, method = "deleteByCond")
	void deleteByCond(MapperParam param);
	
	@SelectProvider(type = MapperProvider.class, method = "findById")
	Map<String,Object> findById(MapperParam param);

	@SelectProvider(type = MapperProvider.class, method = "findListByCond")
	List<Map<String,Object>> findListByCond(MapperParam param);

}