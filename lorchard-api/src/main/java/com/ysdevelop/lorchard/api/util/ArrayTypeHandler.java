package com.ysdevelop.lorchard.api.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * @author USER
 *
 */
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType)
			throws SQLException {
		String[] imagePaths = (String[]) parameter;
		String imagePath = "";
		for (int j = 0; j < imagePaths.length; j++) {
			if (j < imagePaths.length - 1) {
				imagePath += imagePaths[j] + ",";
			} else {
				imagePath += imagePaths[j];
			}
		}
		ps.setString(i, imagePath);
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String imagePath = rs.getString(columnName);
		String[] imagePaths = imagePath.split(",");
		return imagePaths;
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Object[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}

}
