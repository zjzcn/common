package com.zebra.data.mapper.model;

import java.util.ArrayList;
import java.util.List;

public class Where implements SqlBuilder{

	private Op op;
	private String propertyName;
	private Object[] values;

	public Where(Op op, String propertyName, Object...values) {
		this.op = op;
		this.propertyName = propertyName;
		this.values = values;
	}

	@Override
	public SqlInfo buildSql() {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		switch (op) {
		case EQ: {
			sb.append(propertyName).append("=?");
			params.add(values[0]);
			break;
		}
		case NE: {
			sb.append(propertyName).append("<>?");
			params.add(values[0]);
			break;
		}
		case LIKE: {
			sb.append(propertyName).append(" LIKE ?");
			params.add(values[0]);
			break;
		}
		case GT: {
			sb.append(propertyName).append(">?");
			params.add(values[0]);
			break;
		}
		case LT: {
			sb.append(propertyName).append("<?");
			params.add(values[0]);
			break;
		}
		case LE: {
			sb.append(propertyName).append("<=?");
			params.add(values[0]);
			break;
		}
		case GE: {
			sb.append(propertyName).append(">=?");
			params.add(values[0]);
			break;
		}
		case BETWEEN: {
			sb.append(propertyName).append(" between ? and ?");
			params.add(values[0]);
			params.add(values[1]);
			break;
		}
		case IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len - 1; i++) {
				s.append("?, ");
				params.add(values[i]);
			}
			s.append("?");
			params.add(values[len - 1]);

			sb.append(propertyName).append(" IN (").append(s).append(')');
			break;
		}
		case NOT_IN: {
			int len = values.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < len - 1; i++) {
				s.append("?, ");
				params.add(values[i]);
			}
			s.append("?");
			params.add(values[len - 1]);

			sb.append(propertyName).append(" NOT IN (").append(s).append(')');
			break;
		}
		case IS_NULL: {
			sb.append(propertyName).append(" IS NULL");
			break;
		}
		case IS_NOT_NULL: {
			sb.append(propertyName).append(" IS NOT NULL");
			break;
		}
		case AND: {
			sb.append(" AND");
			break;
		}
		case OR: {
			sb.append(" OR");
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

	public enum Op {
		EQ, NE, LIKE, GT, LT, LE, GE, BETWEEN, IN, NOT_IN, IS_NULL, IS_NOT_NULL, AND, OR, LEFT_BRACKET, RIGHT_BRACKET
	}

}
