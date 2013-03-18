package org.esupportail.cookingapp.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/META-INF/applicationContext.xml"})
public class DaoServiceTest {
	
	@Inject
	private DataSource dataSource;
	
	@Inject
	private DaoService daoService;
	
	private IDatabaseConnection connection;

	private final String AN_INGREDIENT = "sel fin";
	
	@Before
	public void setUp() throws Exception {
		final IDataSet dataset = new FlatXmlDataSetBuilder().build(new File("src/test/resources/META-INF/dataset.xml"));
		connection = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}
	
	@After
	public void cleanUp() throws Exception {
		connection.close();		
	}

	@Test
	public void getIngredient() {
		final Ingredient salt = daoService.getIngredient(AN_INGREDIENT);
		assertNotNull("Salt ingredient should have been present!", salt);
	}
	
	@Test
	public void getIngredients() {
		final List<Ingredient> list = daoService.getIngredients();
		assertFalse("List of ingredients must not be empty!", list.isEmpty());
	}
	
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param daoService the daoService to set
	 */
	public void setDaoService(final DaoService daoService) {
		this.daoService = daoService;
	}
	
}
