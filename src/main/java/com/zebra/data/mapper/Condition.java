package com.zebra.data.mapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.zebra.data.mapper.model.GroupBy;
import com.zebra.data.mapper.model.Having;
import com.zebra.data.mapper.model.OrderBy;
import com.zebra.data.mapper.model.Where;

public class Condition {
	
	public static final int DEFLAUT_PAGE_SIZE = 10;
	
    private Class<?> entityClass;
    private boolean distinct = false;
    private List<Where> whereList = new LinkedList<Where>();
    private List<OrderBy> orderByList;
    private List<GroupBy> groupByList;
    private List<Having> havingList;
	private int pageNo = -1;
	private int pageSize = -1;
	
    private Condition(Class<?> entityClass, boolean distinct) {
    	this.entityClass = entityClass;
    	this.distinct = distinct;
    }
    
    public static Condition from(Class<?> entityClass) {
        return new Condition(entityClass, false);
    }
    
	public Condition fromDistinct(Class<?> entityClass) {
		return new Condition(entityClass, true);
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
	
	/************************ page start *************************/
	public Condition page(int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = DEFLAUT_PAGE_SIZE;
		}
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		return this;
	}


	/************************ order group having start *************************/
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
    
    public Condition groupBy() {
    	return this;
    }
    
    public Condition having() {
    	return this;
    }
   
    /************************ get *************************/
	public Class<?> getEntityClass() {
		return entityClass;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Where> getWhereList() {
		return Collections.unmodifiableList(whereList);
	}

	public List<OrderBy> getOrderByList() {
		return Collections.unmodifiableList(orderByList);
	}

	public List<GroupBy> getGroupByList() {
		return Collections.unmodifiableList(groupByList);
	}

	public List<Having> getHavingList() {
		return Collections.unmodifiableList(havingList);
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
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

}
