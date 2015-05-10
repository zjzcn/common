package com.zebra.utils.db.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.zebra.utils.db.Entity;

/**
 * 
 * @author loolly
 *
 */
public class SingleEntityHandler implements RsHandler<Entity>{
	
	/**
	 * 创建一个 SingleEntityHandler对象
	 * @return SingleEntityHandler对象
	 */
	public static SingleEntityHandler create() {
		return new SingleEntityHandler();
	}

	@Override
	public Entity handle(ResultSet rs) throws SQLException {
		final ResultSetMetaData  meta = rs.getMetaData();
		final int columnCount = meta.getColumnCount();
		
		return rs.next() ? HandleHelper.handleRow(columnCount, meta, rs) : null;
	}
}
