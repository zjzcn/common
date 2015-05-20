package com.zebra.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sun.misc.Sort;

/**
 * @author zhangjz
 * @version [v1.0.0, 2012-7-8]
 */
public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int DEFLAUT_PAGE_SIZE = 10;
	
	// -- 分页参数 --//
	private int pageNo = 1;// 页数

	private int pageSize = DEFLAUT_PAGE_SIZE;// 显示条数

	private long totalCount = -1;// 总条数

	private String orderProperty;

	private String orderDirection;

	private List<Order> orderList = new ArrayList<Order>();
	
	private List<T> resultList = new ArrayList<T>();

	public Page() {

	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Page(List<T> resultList) {
		this.resultList = resultList;
	}

	public Page(int pageNo, int pageSize, long totalCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public Page(int pageNo, int pageSize, long totalCount, List<T> resultList) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.resultList = resultList;
	}

	// -- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号如果大于总条数，则当前页定位到总页数
	 */
	public int getPageNo() {
		if (this.getPageCount() > -1 && pageNo > this.getPageCount()) {
			pageNo = (int) this.getPageCount();
		}
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 查询结果list
	 */
	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getPageCount() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count = count + 1;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean hasNextPage() {
		return (pageNo + 1 <= getPageCount());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (hasNextPage()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean hasPrevPage() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrevPage() {
		if (hasPrevPage()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public int getStartRow() {
		return (pageNo - 1) * pageSize + 1;
	}

	public int getEndRow() {
		return pageNo * pageSize;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	//////////////////////////////////////////////////////////////////////////////////
	public static class Order implements Serializable{
		
		private static final long serialVersionUID = 1L;

		private static final Direction DEFAULT_DIRECTION = Direction.ASC;
		private static final boolean DEFAULT_IGNORE_CASE = false;

		private final Direction direction;
		private final String property;
		private final boolean ignoreCase;
		
		/**
		 * Creates a new {@link Order} instance. if order is {@literal null} then order defaults to
		 * {@link Sort#DEFAULT_DIRECTION}
		 * 
		 * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
		 * @param property must not be {@literal null} or empty.
		 */
		public Order(Direction direction, String property) {

			this(direction, property, DEFAULT_IGNORE_CASE);
		}

		/**
		 * Creates a new {@link Order} instance. Takes a single property. Direction defaults to
		 * {@link Sort#DEFAULT_DIRECTION}.
		 * 
		 * @param property must not be {@literal null} or empty.
		 */
		public Order(String property) {
			this(DEFAULT_DIRECTION, property);
		}

		/**
		 * Creates a new {@link Order} instance. if order is {@literal null} then order defaults to
		 * {@link Sort#DEFAULT_DIRECTION}
		 * 
		 * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
		 * @param property must not be {@literal null} or empty.
		 * @param ignoreCase true if sorting should be case insensitive. false if sorting should be case sensitive.
		 */
		private Order(Direction direction, String property, boolean ignoreCase) {
			this.direction = direction == null ? DEFAULT_DIRECTION : direction;
			this.property = property;
			this.ignoreCase = ignoreCase;
		}

		/**
		 * Returns the order the property shall be sorted for.
		 * 
		 * @return
		 */
		public Direction getDirection() {
			return direction;
		}

		/**
		 * Returns the property to order for.
		 * 
		 * @return
		 */
		public String getProperty() {
			return property;
		}

		/**
		 * Returns whether sorting for this property shall be ascending.
		 * 
		 * @return
		 */
		public boolean isAscending() {
			return this.direction.equals(Direction.ASC);
		}

		/**
		 * Returns whether or not the sort will be case sensitive.
		 * 
		 * @return
		 */
		public boolean isIgnoreCase() {
			return ignoreCase;
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////
	public static enum Direction {

		ASC, DESC;

		/**
		 * Returns the {@link Direction} enum for the given {@link String} value.
		 * 
		 * @param value
		 * @throws IllegalArgumentException in case the given value cannot be parsed into an enum value.
		 * @return
		 */
		public static Direction fromString(String value) {

			try {
				return Direction.valueOf(value.toUpperCase(Locale.US));
			} catch (Exception e) {
				throw new IllegalArgumentException(String.format(
						"Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
			}
		}

		/**
		 * Returns the {@link Direction} enum for the given {@link String} or null if it cannot be parsed into an enum
		 * value.
		 * 
		 * @param value
		 * @return
		 */
		public static Direction fromStringOrNull(String value) {

			try {
				return fromString(value);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	}
}
