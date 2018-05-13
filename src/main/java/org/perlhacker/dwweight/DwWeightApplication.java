package org.perlhacker.dwweight;

import org.perlhacker.dwweight.db.EntryDAO;
import org.perlhacker.dwweight.resources.WeightResource;
import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DwWeightApplication extends Application<DwWeightConfiguration> {

	public static void main(final String[] args) throws Exception {
		new DwWeightApplication().run(args);
	}

	@Override
	public String getName() {
		return "DwWeight";
	}

	@Override
	public void initialize(final Bootstrap<DwWeightConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
	}

	@Override
	public void run(final DwWeightConfiguration config, final Environment environment) {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "h2");
		
		final EntryDAO dao = jdbi.onDemand(EntryDAO.class);
		final WeightResource weightResource = new WeightResource(dao);
		
		environment.jersey().register(weightResource);
	}
}