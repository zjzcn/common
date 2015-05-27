package com.zebra.data.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zebra.data.mapper.EntityHelper.EntityColumn;
import com.zebra.data.mapper.model.Where;
import com.zebra.data.mapper.model.Where.Op;


public class MybatisUtils {

	public static SqlInfo buildCondition(Condition cond) {
		List<Where> wheres = cond.getWhereList();
		SqlInfo sql = new SqlInfo();
		for(Where where : wheres) {
			SqlInfo sqlInfo = buildWhere(where, cond.getEntityClass());
			sql.addSqlInfo(sqlInfo);
		}
		return sql;
	}
	
	public static SqlInfo buildWhere(Where where, Class<?> entityClass) {
		Op op = where.getOp();
		String propertyName = where.getPropertyName();
		Object[] values = where.getValues();
		
		EntityColumn column = EntityHelper.getColumn(entityClass, propertyName);
		String columnName = column.getColumn();
		
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		switch (op) {
		case EQ: {
			sb.append(columnName).append("=#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case NE: {
			sb.append(columnName).append("<>#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case LIKE: {
			sb.append(columnName).append(" LIKE #{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case GT: {
			sb.append(columnName).append(">#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case LT: {
			sb.append(columnName).append("<#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case LE: {
			sb.append(columnName).append("<=#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case GE: {
			sb.append(columnName).append(">=#{params."+propertyName+"}");
			params.put(propertyName, values[0]);
			break;
		}
		case BETWEEN: {
			String propertyName0 = propertyName+"0";
			String propertyName1 = propertyName+"1";
			sb.append(columnName).append(" BETWEEN #{params."+propertyName0+"} AND #{params."+propertyName1+"}");
			params.put(propertyName0, values[0]);
			params.put(propertyName1, values[1]);
			break;
		}
		case IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len ; i++) {
				s.append("#{params."+propertyName+i+"}, ");
				params.put(propertyName+i, values[i]);
			}
			s.deleteCharAt(s.length()-1);
			sb.append(columnName).append(" IN (").append(s).append(") ");
			break;
		}
		case NOT_IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len ; i++) {
				s.append("#{params."+propertyName+i+"}, ");
				params.put(propertyName+i, values[i]);
			}
			s.deleteCharAt(s.length()-1);
			sb.append(columnName).append(" NOT IN (").append(s).append(") ");
			break;
		}
		case IS_NULL: {
			sb.append(columnName).append(" IS NULL ");
			break;
		}
		case IS_NOT_NULL: {
			sb.append(columnName).append(" IS NOT NULL ");
			break;
		}
		case AND: {
			sb.append(" AND ");
			break;
		}
		case OR: {
			sb.append(" OR ");
			break;
		}
		case LEFT_BRACKET: {
			sb.append(" (");
			break;
		}
		case RIGHT_BRACKET: {
			sb.append(") ");
			break;
		}
		default: {
			//nothing
		}
		}
		return new SqlInfo(sb.toString(), params);
	}
}
