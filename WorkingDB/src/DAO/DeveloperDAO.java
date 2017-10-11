package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Company;
import Model.Developer;
import Model.Project;
import Service.DeveloperService;

public class DeveloperDAO {
	

	public static Developer getDeveloper(String name, String surname) throws Exception{
		Developer developer = new Developer();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM developers where NameDevelopers=? and SurnameDevelopers=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getDeveloper. Connection is not established!");
				return developer;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, surname);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						developer.setId(rSet.getInt("idDevelopers"));
						developer.setName (rSet.getString("NameDevelopers"));
						developer.setSurname(rSet.getString("SurnameDevelopers"));
						developer.setProject(ProjectDAO.getProject(rSet.getInt("id_project")));
						developer.setCompany(CompanyDAO.getCompany(rSet.getInt("id_comp")));
						developer.setSalary(rSet.getInt("salary"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				e.printStackTrace();
				return developer;
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return developer;
	}
	
	
	public static void setDeveloper(Developer developer) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		
				
				/*"update developers set  NameDevelopers=?, SurnameDeveloper=?, id_project=?, "
				+ "id_comp=?, salary=? where idDevelopers='" + developer.getId() + "';";*/
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setDeveloper. Connection is not established!");
			}
			try {
				statement = connection.createStatement();
				String sql = DeveloperService.queryBuilderUpdate(developer);
			/*	prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, developer.getName());
					prStatement.setString(2, developer.getSurname());
					prStatement.setInt(3, developer.getProject().getIdProject());
					prStatement.setInt(4, developer.getCompany().getIdCompany());
					prStatement.setInt(5, developer.getSalary());
				prStatement.executeUpdate();*/
				
				statement.executeUpdate(sql);
			}
			catch (SQLException e) {
				System.err.println("Method setDeveloper. SQL request isn't correct.");
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static void addDeveloper(Developer developer) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		Developer checkDev = getDeveloper(developer.getName(), developer.getSurname());
		//System.out.println(checkDev.toString() + "\n" + developer.toString());
		try {
		if(checkDev.getName().equals(developer.getName()) && 
				checkDev.getSurname().equals(developer.getSurname()) && 
				(checkDev.getProject().getNameProject().equals(developer.getProject().getNameProject()) || 
				checkDev.getCompany().getNameCompany().equals(developer.getCompany().getNameCompany()))) {
			System.err.println("Developer already exist");
			return;
		}
		} catch (NullPointerException e){
			
		}
		
				/*"Insert into developers (NameDevelopers, SurnameDevelopers, id_project, id_comp, salary)"
				+ " values (?, ?, ?, ?, ?);";*/
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method addDeveloper. Connection is not established!");
			}
			try {
				statement = connection.createStatement();
				String sql = DeveloperService.queryBuilderAdd(developer);
				System.out.println(sql);
				statement.executeUpdate(sql);
				/*
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, developer.getName());
					prStatement.setString(2, developer.getSurname());
					if (developer.getProject() == null){
						prStatement.setInt(3, 0);
					}
					else{
						prStatement.setInt(3, developer.getProject().getIdProject());
					}
					if(developer.getProject() != null) {
						prStatement.setInt(4, ProjectDAO.getIdCompAdd(developer.getProject()));
					} else {
						if (developer.getCompany().getIdCompany() != null){
							prStatement.setInt(4, developer.getCompany().getIdCompany());
						}else {
							Integer idComp = CompanyDAO.getIdComp(developer.getCompany());
							developer.getCompany().setIdCompany(idComp);
							prStatement.setInt(4, idComp);
						}
					}
					if(developer.getSalary() != null) {
						prStatement.setInt(5, developer.getSalary());
					}else {
						prStatement.setInt(5, 0);
					}
				prStatement.executeUpdate();*/
			}
			catch (SQLException e) {
				System.err.println("Method addDeveloper. SQL request isn't correct.");
				e.printStackTrace();
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method addUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static void removeDeveloper(String name, String surname) throws Exception{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		Developer developer = getDeveloper(name, surname);
		String sql = "SELECT * FROM developers where NameDevelopers=? and SurnameDevelopers=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeDeveloper. Connection is not established!");
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, name);
				statement.setString(2, surname);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(developer != null){
						statement.executeUpdate("DELETE FROM developers WHERE namedevelopers='" + name + 
								"' and surnamedevelopers='" + surname + "';");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeDeveloper. SQL request isn't correct.");
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method deleteDeveloper. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}

}
