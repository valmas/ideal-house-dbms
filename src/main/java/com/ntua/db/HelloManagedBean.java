package com.ntua.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.jdbc.JdbcBaseDao;

@ApplicationScoped
@Named("helloWorldBean")
public class HelloManagedBean {

	private Connection connection;
	
	@Inject
	private JdbcBaseDao jdbc;
	
	private List<Person> results;
	
	
	public String init(){
		return "/index.xhtml?faces-redirect=true";
	}

	public JdbcBaseDao getJdbc() {
		return jdbc;
	}

	public void setJdbc(JdbcBaseDao jdbc) {
		this.jdbc = jdbc;
	}
	
	public void doQuery() throws SQLException{
		connection = jdbc.getJdbcConnection();
		Statement statement = connection.createStatement();
		String queryString = "Select * from Persons";
		ResultSet resultSet = statement.executeQuery(queryString);
		results = new ArrayList<Person>();
		while (resultSet.next()) {
			Person person = new Person();
			person.setPid(resultSet.getString("P_Id"));
			person.setLastName(resultSet.getString("LastName"));
			person.setFirstName(resultSet.getString("FirstName"));
			person.setAddress(resultSet.getString("Address"));
			person.setCity(resultSet.getString("City"));
			person.setBirth(resultSet.getDate("birth"));
			results.add(person);
		}
		jdbc.freeResultSet(resultSet);
		jdbc.freeStatement(statement);
		jdbc.closeConnection(connection);
		
	}

	public List<Person> getResults() {
		return results;
	}

	public void setResults(List<Person> results) {
		this.results = results;
	}
	
	
}
