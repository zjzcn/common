package com.zebra.data.mapper;

import java.util.LinkedHashMap;
import java.util.Map;

import com.zebra.data.mapper.model.Where;
import com.zebra.data.mapper.model.Where.Op;


public class MybatisUtils {

	public static SqlInfo buildWhere(Where where) {
		Op op = where.getOp();
		String propertyName = where.getPropertyName();
		Object[] values = where.getValues();
		
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		switch (op) {
		case EQ: {
			sb.append(propertyName).append("=?");
			params.put(propertyName, values[0]);
			break;
		}
		case NE: {
			sb.append(propertyName).append("<>?");
			params.put(propertyName, values[0]);
			break;
		}
		case LIKE: {
			sb.append(propertyName).append(" LIKE ?");
			params.put(propertyName, values[0]);
			break;
		}
		case GT: {
			sb.append(propertyName).append(">?");
			params.put(propertyName, values[0]);
			break;
		}
		case LT: {
			sb.append(propertyName).append("<?");
			params.put(propertyName, values[0]);
			break;
		}
		case LE: {
			sb.append(propertyName).append("<=?");
			params.put(propertyName, values[0]);
			break;
		}
		case GE: {
			sb.append(propertyName).append(">=?");
			params.put(propertyName, values[0]);
			break;
		}
		case BETWEEN: {
			sb.append(propertyName).append(" BETWEEN ? AND ?");
			params.put(propertyName, values[0]);
			params.put(propertyName, values[1]);
			break;
		}
		case IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len - 1; i++) {
				s.append("?, ");
				params.put(propertyName, values[i]);
			}
			s.append("?");
			params.put(propertyName, values[len - 1]);

			sb.append(propertyName).append(" IN (").append(s).append(") ");
			break;
		}
		case NOT_IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len - 1; i++) {
				s.append("?, ");
				params.put(propertyName, values[i]);
			}
			s.append("?");
			params.put(propertyName, values[len - 1]);

			sb.append(propertyName).append(" NOT IN (").append(s).append(") ");
			break;
		}
		case IS_NULL: {
			sb.append(propertyName).append(" IS NULL ");
			break;
		}
		case IS_NOT_NULL: {
			sb.append(propertyName).append(" IS NOT NULL ");
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
