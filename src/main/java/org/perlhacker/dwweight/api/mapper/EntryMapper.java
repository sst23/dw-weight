package org.perlhacker.dwweight.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.perlhacker.dwweight.api.Entry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class EntryMapper implements ResultSetMapper<Entry> {

	@Override
	public Entry map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Entry(r.getInt("date"), r.getFloat("weight"));
	}

}