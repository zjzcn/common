package com.zebra.utils.db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map.Entry;

import com.zebra.utils.Conver;
import com.zebra.utils.InjectUtil;
import com.zebra.utils.StrUtil;
import com.zebra.utils.exceptions.UtilException;

/**
 * 数据实体对象<br>
 * 数据实体类充当两个角色：<br>
 * 1. 数据的载体，一个Entity对应数据库中的一个row<br>
 * 2. SQL条件，Entity中的每一个字段对应一个条件，字段值对应条件的值
 * @author loolly
 *
 */
public class Entity extends HashMap<String, Object>{
	private static final long serialVersionUID = -1951012511464327448L;
	
	//--------------------------------------------------------------- Static method start
	public static Entity create(String tableName) {
		return new Entity(tableName);
	}
	//--------------------------------------------------------------- Static method end
	
	private String tableName;
	
	//--------------------------------------------------------------- Constructor start
	public Entity() {
	}
	
	/**
	 * 构造
	 * @param tableName 数据表名
	 */
	public Entity(String tableName) {
		this.tableName = tableName;
	}
	//--------------------------------------------------------------- Constructor end
	
	//--------------------------------------------------------------- Getters and Setters start
	/**
	 * @return 获得表名
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * 设置表名
	 * @param tableName 表名
	 * @return 本身
	 */
	public Entity setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	//--------------------------------------------------------------- Getters and Setters end
	
	/**
	 * 填充Value Object对象
	 * @param vo Value Object（或者POJO）
	 * @return vo
	 */
	public <T> T fillVo(T vo) {
		InjectUtil.injectFromMap(vo, this);
		return vo;
	}
	
	/**
	 * 填充Value Object对象
	 * @param clazz Value Object（或者POJO）的类
	 * @return vo
	 */
	public <T> T toVo(Class<T> clazz) {
		if(clazz == null) {
			throw new NullPointerException("Provided Class is null!");
		}
		T vo;
		try {
			vo = clazz.newInstance();
		} catch (Exception e) {
			throw new UtilException(StrUtil.format("Instance Value Object [] error!", clazz.getName()));
		}
		InjectUtil.injectFromMap(vo, this);
		return vo;
	}
	
	/**
	 * 将值对象转换为Entity<br>
	 * 类名会被当作表名，小写第一个字母
	 * @param vo 值对象
	 * @return 自己
	 */
	public Entity parse(Object vo) {
		String tableName = vo.getClass().getSimpleName();
		tableName = StrUtil.lowerFirst(tableName);
		this.setTableName(tableName);
		
		this.putAll(InjectUtil.toMap(vo, false));
		return this;
	}
	
	/**
	 * 与给定实体对比并去除相同的部分<br>
	 * 此方法用于在更新操作时避免所有字段被更新，跳过不需要更新的字段
	 * version from 2.0.0
	 * @param entity
	 */
	public <T extends Entity> void removeEqual(T entity) {
		for(Entry<String, Object> entry : entity.entrySet()) {
			final Object value = this.get(entry.getKey());
			if(null != value && value.equals(entry.getValue())) {
				this.remove(entry.getKey());
			}
		}
	}
	
	//-------------------------------------------------------------------- 特定类型值
	/**
	 * 设置列
	 * @param attr 属性
	 * @param value 值
	 * @return 本身
	 */
	public Entity set(String attr, Object value) {
		super.put(attr, value);
		return this;
	}
	
	/**
	 * 设置列，当键或值为null时忽略
	 * @param attr 属性
	 * @param value 值
	 * @return 本身
	 */
	public Entity setIgnoreNull(String attr, Object value) {
		if(null != attr && null != value) {
			set(attr, value);
		}
		return this;
	}
	
	/**
	 * 获得特定类型值
	 * @param attr 字段名
	 * @param defaultValue 默认值
	 * @return 字段值
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String attr, T defaultValue) {
		final Object result = get(attr);
		return (T)(result != null ? result : defaultValue);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public String getStr(String attr) {
		return Conver.toStr(get(attr), null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Integer getInt(String attr) {
		return Conver.toInt(get(attr), null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Long getLong(String attr) {
		return Conver.toLong(get(attr), null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Float getFloat(String attr) {
		return Conver.toFloat(get(attr), null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Boolean getBool(String attr) {
		return Conver.toBool(get(attr), null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public byte[] getBytes(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Date getDate(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Time getTime(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Timestamp getTimestamp(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public Number getNumber(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public BigDecimal getBigDecimal(String attr) {
		return get(attr, null);
	}
	
	/**
	 * @param attr 字段名
	 * @return 字段值
	 */
	public BigInteger getBigInteger(String attr) {
		return get(attr, null);
	}
}
