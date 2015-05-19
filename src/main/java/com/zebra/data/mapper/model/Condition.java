package com.zebra.data.mapper.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zjzcn.helper.query.Page;
import com.zjzcn.helper.query.Select;
import com.zjzcn.helper.query.filter.Filter;
import com.zjzcn.helper.query.filter.OrderbyFilter;
import com.zjzcn.helper.query.filter.PageFilter;
import com.zjzcn.helper.query.filter.WhereFilter;

public class Condition implements SqlBuilder {
	
    private Class<?> entityClass;
    private boolean distinct = false;
    private List<Where> whereList;
    private List<OrderBy> orderByList;
    private List<GroupBy> groupByList;
    private List<Having> havingList;
	private int pageNo = -1;
	private int pageSize = -1;
	
    private Condition(Class<?> entityClass) {
    	this.entityClass = entityClass;
    }
    
    public static Condition from(Class<?> entityClass) {
        return new Condition(entityClass);
    }
    
	public Condition distinct() {
		this.distinct = true;
		return this;
	}
	
	/************************ where start *************************/
	/**
	 * 匹配全部
	 */
	public Condition like(String propertyName, String value) {
		if (isBlank(value)) {
			return this;
		}
		Where where = new Where(Where.Op.LIKE, propertyName, value.trim());
		whereList.add(where);
		return this;
	}

	/**
	 * 相等
	 */
	public Condition eq(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.EQ, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 不等
	 */
	public Condition ne(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.NE, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 大于
	 */
	public Condition gt(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.GT, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 小于
	 */
	public Condition lt(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.LT, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 小于等于
	 */
	public Condition le(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.LE, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 大于等于
	 */
	public Condition ge(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}
		Where where = new Where(Where.Op.GE, propertyName, value);
		whereList.add(where);
		return this;
	}

	/**
	 * 范围
	 */
	public Condition between(String propertyName, Object value1, Object value2) {
		if (value1 == null || value2 == null) {
			return this;
		}
		Where where = new Where(Where.Op.BETWEEN, propertyName, new Object[]{value1,value2});
		whereList.add(where);
		return this;
	}

	/**
	 * sql中的in
	 */
	public Condition in(String propertyName, Object[] values) {
		if (values == null || values.length == 0) {
			return this;
		}
		Where where = new Where(Where.Op.IN, propertyName, values);
		whereList.add(where);
		return this;
	}

	/**
	 * sql中的not in
	 */
	public Condition notIn(String propertyName, Object[] values) {
		if (values == null || values.length == 0) {
			return this;
		}
		Where where = new Where(Where.Op.NOT_IN, propertyName, values);
		whereList.add(where);
		return this;
	}

	public Condition isNull(String propertyName) {
		Where where = new Where(Where.Op.IS_NULL, propertyName);
		whereList.add(where);
		return this;
	}

	public Condition isNotNull(String propertyName) {
		Where where = new Where(Where.Op.IS_NOT_NULL, propertyName);
		whereList.add(where);
		return this;
	}
	
	public Condition and() {
		Where where = new Where(Where.Op.AND, null);
		whereList.add(where);
		return this;
	}

	public Condition or() {
		Where where = new Where(Where.Op.OR, null);
		whereList.add(where);
		return this;
	}

	public Condition leftBracket() {
		Where where = new Where(Where.Op.LEFT_BRACKET, null);
		whereList.add(where);
		return this;
	}

	public Condition rightBracket() {
		Where where = new Where(Where.Op.RIGHT_BRACKET, null);
		whereList.add(where);
		return this;
	}
	
	/************************ order by start *************************/
	public Condition orderByAsc(String propertyName) {
		OrderBy orderBy = new OrderBy(OrderBy.Op.ASC, propertyName);
		orderByList.add(orderBy);
		return this;
	}

	public Condition orderByDesc(String propertyName) {
		OrderBy orderBy = new OrderBy(OrderBy.Op.DESC, propertyName);
		orderByList.add(orderBy);
		return this;
	}

	/************************ page start *************************/
	public Condition page(int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Page.DEFLAUT_PAGE_SIZE;
		}
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		return this;
	}

	@Override
	public SqlInfo buildSql() {
		StringBuilder sb = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();

		StringBuilder whereBuilder = new StringBuilder();
		StringBuffer orderbyBuilder = new StringBuffer();
		Page<?> page = null;
		// 遍历参数，组合where和参数
		for (Filter filter : filers) {
			if (filter instanceof WhereFilter) {
				WhereFilter whereFilter = (WhereFilter) filter;
				whereBuilder.append(" and ").append(whereFilter.toQueryString());
				paramList.addAll(whereFilter.getParamList());
			} else if (filter instanceof OrderbyFilter) {
				orderbyBuilder.append(filter.toQueryString()).append(", ");
			} else if (filter instanceof PageFilter) {
				page = ((PageFilter) filter).getPage();
			}
		}
		sb.append(" from ").append(getShortClassName(clazz.getName())).append(" where 1=1")
				.append(whereBuilder).append(orderbyBuilder.length() > 0 ? " order by " : " ")
				.append(StringUtils.removeEnd(orderbyBuilder.toString(), ", "));
		int i = 0;
		for (int idx = 0; idx != -1; i++) {
			idx = sb.indexOf("?", idx + 1);
			if (idx != -1) {
				sb.insert(idx + 1, i);
			}
		}

		return new SqlInfo(pageNo, pageSize, sb.toString(), paramList);

	}

	/************************ private *************************/
	private static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将包名+类名转换成类名
	 * 
	 * @param className
	 * @return
	 */
	private static String getShortClassName(String className) {
		if (className == null) {
			return "";
		}
		if (className.length() == 0) {
			return "";
		}

		int lastDotIdx = className.lastIndexOf('.');
		int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
		String out = className.substring(lastDotIdx + 1);
		if (innerIdx != -1) {
			out = out.replace('$', '.');
		}
		return out;
	}
	
    public Condition orderBy() {
    	return this;
    }
    
    public Condition groupBy() {
    	return this;
    }
    
    public Condition having() {
    	return this;
    }
    
}
