package com.models;

import java.sql.*;

public abstract class Dto<T> {
	public abstract T setResultSet(ResultSet rs) throws SQLException;
}
