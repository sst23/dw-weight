package org.perlhacker.dwweight.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.perlhacker.dwweight.api.Entry;
import org.perlhacker.dwweight.db.EntryDAO;

@Path("/weight")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WeightResource {
	private EntryDAO entryDao;

	public WeightResource(EntryDAO entryDao) {
		this.entryDao = entryDao;
		entryDao.createEntriesTable();
	}

	@GET
	public List<Entry> getAll() {
		return entryDao.getAll();
	}

	@POST
	public Entry add(@Valid Entry entry) {
		entryDao.insert(entry);
		return entry;
	}
}