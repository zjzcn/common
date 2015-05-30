package com.zebra.data.mapper;

import java.util.Map;

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
	
	public String deleteById(Map<String, Object> params) {
		final Class<?> entityClass = (Class<?>)params.get("entityClass");
		final Object id = params.get("id");
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

	public String deleteByCond(Map<String, Object> params) {
		final Condition cond = (Condition)params.get("cond");
		final SqlInfo sqlInfo = MybatisUtils.buildCondition(cond);
		params.clear();
		params.putAll(sqlInfo.getParams());
		return new SQL() {{
			EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(cond.getEntityClass());
            DELETE_FROM(entityTable.getName());
            WHERE(sqlInfo.getSql());
        }}.toString();
	}
	public String findById(Map<String, Object> params) {
		final Class<?> entityClass = (Class<?>)params.get("entityClass");
		final Object id = params.get("id");
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

	public String findListByCond(Map<String, Object> params) {
		final Condition cond = (Condition)params.get("cond");
		final SqlInfo sqlInfo = MybatisUtils.buildCondition(cond);
		params.clear();
		params.putAll(sqlInfo.getParams());
		return new SQL() {{
			EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(cond.getEntityClass());
            SELECT(EntityHelper.getAllColumns(cond.getEntityClass()));
            FROM(entityTable.getName());
            WHERE(sqlInfo.getSql());
        }}.toString();
	}

	public String countByCond(Map<String, Object> params) {
		final Condition cond = (Condition)params.get("cond");
		final SqlInfo sqlInfo = MybatisUtils.buildCondition(cond);
		params.clear();
		params.putAll(sqlInfo.getParams());
		return new SQL() {{
			EntityHelper.EntityTable entityTable = EntityHelper.getEntityTable(cond.getEntityClass());
            SELECT("COUNT(*)");
            FROM(entityTable.getName());
            WHERE(sqlInfo.getSql());
        }}.toString();
	}
	
	public String findListBySql(Map<String, Object> params) {
		return convertSql(params);
	}

	public String insertBySql(Map<String, Object> params) {
		return convertSql(params);
	}
	
	public String updateBySql(Map<String, Object> params) {
		return convertSql(params);
	}
	
	public String deleteBySql(Map<String, Object> params) {
		return convertSql(params);
	}
	
	private String convertSql(Map<String, Object> params) {
		final String sql = (String)params.get("sql");
		final Object[] sqlParams = (Object[])params.get("sqlParams");
		params.clear();
		
		StringBuilder sb = new StringBuilder(sql);
		for (int i = 0,idx = -2; idx != -1; i++) {
			if(idx==-2) {
				idx = sb.indexOf("?");
			} else {
				idx = sb.indexOf("?", idx + 1);
			}
			if (idx != -1) {
				String param = "param"+i;
				sb.replace(idx, idx + 1, "#{"+param+"}");
				params.put(param, sqlParams[i]);
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
//		Map<String, Object> params = new HashMap<String, Object>() {{
//			put("sql", "?dddddddddddddd?dddddddddddd?ddddddd?dddddddd");
//			put("params", new Object[]{1, "ddd", 4});
//		}};
//		System.out.println(findListBySql(params));
	}
}
