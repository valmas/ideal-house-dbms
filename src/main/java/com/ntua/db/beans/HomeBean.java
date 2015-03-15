package com.ntua.db.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.common.Presentation;
import com.ntua.db.common.Queries;
import com.ntua.db.jdbc.JdbcBaseDao;

@ApplicationScoped
@Named("homeBean")
public class HomeBean {
	
	private List<Presentation> mostWanted;
	
	private List<Presentation> bestClients;
	
	private BigDecimal highSal;
	
	private BigDecimal lowSal;
	
	private BigDecimal avgSal;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	@PostConstruct 
	public String init(){
		selectMostWanted();
		selectBestClients();
		prepareEmpStats();
		jdbc.closeConnection(connection);
		return "/index.xhtml?faces-redirect=true";
	}
	
	public void selectMostWanted(){
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			if (connection == null || connection.isClosed())
				connection = jdbc.getJdbcConnection();
			statement = connection.createStatement();
			String queryString = Queries.MOST_WANTED;
			resultSet = statement.executeQuery(queryString);
			mostWanted = new ArrayList<Presentation>();
			while (resultSet.next()) {
				Presentation presentation = new Presentation();
				presentation.setSize(resultSet.getBigDecimal("Size"));
				String floor = resultSet.getString("Floor");
				presentation.setFloor(floor == null ? null : Integer.parseInt(floor));
				presentation.setRent(resultSet.getBigDecimal("Rent"));
				presentation.setStreetName(resultSet.getString("Addr_StreetName"));
				presentation.setStreetNo(resultSet.getString("Addr_StreetNo"));
				presentation.setPostalCode(resultSet.getString("Addr_PostalCode"));
				String rooms = resultSet.getString("Rooms");
				presentation.setRooms(rooms == null ? null : Integer.parseInt(rooms));
				presentation.setDescription(resultSet.getString("Description"));
				presentation.setStatus(resultSet.getString("Status"));
				presentation.setContracts_num(resultSet.getInt("contracts_num"));
				mostWanted.add(presentation);
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	public void selectBestClients(){
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			if (connection == null || connection.isClosed())
				connection = jdbc.getJdbcConnection();
			statement = connection.createStatement();
			String queryString = Queries.BEST_CLIENTS;
			resultSet = statement.executeQuery(queryString);
			bestClients = new ArrayList<Presentation>();
			while (resultSet.next()) {
				Presentation presentation = new Presentation();
				presentation.setRent(resultSet.getBigDecimal("totalRent"));
				presentation.setFirstName(resultSet.getString("FirstName"));
				presentation.setLastName(resultSet.getString("LastName"));
				bestClients.add(presentation);
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	public void prepareEmpStats(){
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			if (connection == null || connection.isClosed())
				connection = jdbc.getJdbcConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.HIGHEST_SALARY);
			resultSet.next();
			highSal = resultSet.getBigDecimal("max(salary)");
			jdbc.freeResultSet(resultSet);
			resultSet = statement.executeQuery(Queries.LOWEST_SALARY);
			resultSet.next();
			lowSal = resultSet.getBigDecimal("min(salary)");
			jdbc.freeResultSet(resultSet);
			resultSet = statement.executeQuery(Queries.AVERAGE_SALARY);
			resultSet.next();
			avgSal = resultSet.getBigDecimal("avg(salary)").setScale(2, RoundingMode.CEILING);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}

	public List<Presentation> getMostWanted() {
		return mostWanted;
	}

	public void setMostWanted(List<Presentation> mostWanted) {
		this.mostWanted = mostWanted;
	}

	public List<Presentation> getBestClients() {
		return bestClients;
	}

	public void setBestClients(List<Presentation> bestClients) {
		this.bestClients = bestClients;
	}

	public BigDecimal getHighSal() {
		return highSal;
	}

	public void setHighSal(BigDecimal highSal) {
		this.highSal = highSal;
	}

	public BigDecimal getLowSal() {
		return lowSal;
	}

	public void setLowSal(BigDecimal lowSal) {
		this.lowSal = lowSal;
	}

	public BigDecimal getAvgSal() {
		return avgSal;
	}

	public void setAvgSal(BigDecimal avgSal) {
		this.avgSal = avgSal;
	}
	
}
