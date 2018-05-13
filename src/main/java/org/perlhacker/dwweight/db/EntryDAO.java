package org.perlhacker.dwweight.db;

import java.util.List;

import org.perlhacker.dwweight.api.Entry;
import org.perlhacker.dwweight.api.mapper.EntryMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(EntryMapper.class)
public interface EntryDAO {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS entries (date int PRIMARY KEY, weight FLOAT)")
	void createEntriesTable();

	@SqlUpdate("INSERT INTO entries (date, weight) VALUES (:date, :weight)")
	void insert(@BindBean Entry entry);

	@SqlQuery("SELECT * FROM entries")
	List<Entry> getAll();
}