package org.perlhacker.dwweight.resources;

import org.junit.ClassRule;
import org.junit.Test;
import org.perlhacker.dwweight.api.Entry;
import org.perlhacker.dwweight.db.EntryDAO;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WeightResourceTest {

	private static final EntryDAO dao = mock(EntryDAO.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new WeightResource(dao))
			.build();

	@Test
	public void getAll() throws Exception {
		List<Entry> entries = new ArrayList<Entry>();
		entries.add(new Entry(1514764800, 85.58f));
		entries.add(new Entry(1514937600, 83.38f));
		when(dao.getAll()).thenReturn(entries);

		List<Entry> result = resources.client().target("/weight").request().get(new GenericType<List<Entry>>() {
		});
		assertEquals(2, result.size());
		assertEquals(1514764800, result.get(0).getDate());
	}

	@Test
	public void add() throws Exception {
		Entry entry = new Entry(1514764800, 85.58f);
		Entry result = resources.client().target("/weight").request(MediaType.APPLICATION_JSON).post(Entity.json(entry),
				Entry.class);

		assertEquals(entry.getDate(), result.getDate());
		verify(dao, times(1)).insert(any(Entry.class));
	}
}