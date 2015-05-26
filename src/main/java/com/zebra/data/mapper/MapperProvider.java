package com.zebra.data.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.MetaObject;

public class MapperProvider {

	public <T> String insert(final T entity) {
        return new SQL() {{
            EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(entity.getClass());
            INSERT_INTO(entityTable.getName());
            for (EntityHelper.EntityColumn column : entityTable.getEntityClassColumns()) {
                VALUES(column.getColumn(), "#{" + column.getProperty() + "}");
            }
        }}.toString();
	}

	public <T> String update(final T entity) {
		final Class<?> entityClass = entity.getClass();
		return new SQL() {{
			EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
			MetaObject metaObject = MapperTemplate.forObject(entity);
			UPDATE(entityTable.getName());
			for (EntityHelper.EntityColumn column : entityTable.getEntityClassColumns()) {
				//更新不是ID的字段，因为根据主键查询的...更新后还是一样。
				if (!column.isId()) {
					SET(column.getColumn() + "=#{" + column.getProperty() + "}");
				}
			}
			for (EntityHelper.EntityColumn column : entityTable.getEntityClassPKColumns()) {
				MapperHelper1.notNullKeyProperty(column.getProperty(), MapperTemplate.forObject(entity).getValue(column.getProperty()));
				WHERE(column.getColumn() + "=#{" + column.getProperty() + "}");
			}
		}}.toString();
	}
	
	public String deleteById(MapperParam param) {
		final Class<?> entityClass = param.getEntityClass();
		final Object id = param.getId();
		return new SQL() {{
            EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
            DELETE_FROM(entityTable.getName());
            if (entityTable.getEntityClassPKColumns().size() == 1) {
                EntityHelper.EntityColumn column = entityTable.getEntityClassPKColumns().iterator().next();
                MapperHelper1.notNullKeyProperty(column.getProperty(), id);
                WHERE(column.getColumn() + "=#{id}");
            } else {
            	throw new RuntimeException("暂时不支持联合主键");
            }
        }}.toString();
	}

	public String deleteByCond(MapperParam param) {
		// TODO Auto-generated method stub
		return null;
	}
	public String findById(MapperParam param) {
		final Class<?> entityClass = param.getEntityClass();
		final Object id = param.getId();
        return new SQL() {{
            EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
            SELECT(EntityHelper.getAllColumns(entityClass));
            FROM(entityTable.getName());
            if (entityTable.getEntityClassPKColumns().size() == 1) {
                EntityHelper.EntityColumn column = entityTable.getEntityClassPKColumns().iterator().next();
                MapperHelper1.notNullKeyProperty("id", id);
                WHERE(column.getColumn() + "=#{id}");
            } else {
            	throw new RuntimeException("暂时不支持联合主键");
            }
        }}.toString();
	}

	public String findListByCond(MapperParam param) {
		return null;
	}

}
