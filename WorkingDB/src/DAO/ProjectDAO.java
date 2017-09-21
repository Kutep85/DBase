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
import Service.ProjectService;

public class ProjectDAO {

	public ProjectDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Project getProject(String nameProject) throws Exception {
		Project project = new Project();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM projects where name_project=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getProject. Connection is not established!");
				return project;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameProject);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						project.setIdProject(rSet.getInt("idProjects"));
						project.setNameProject(rSet.getString("name_project"));
						project.setCompany(CompanyDAO.getCompany(rSet.getInt("id_company")));
						project.setCustomer(CustomerDAO.getCustomer(rSet.getInt("id_customer")));
						project.setCost(rSet.getInt("cost"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getProject. SQL request isn't correct.");
				e.printStackTrace();
				return project;
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
				System.err.println("Method getProject. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return project;
	}
	
	public static Project getProject(Integer idProj) throws Exception {
		Project project = new Project();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM projects where idProjects=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getProject. Connection is not established!");
				return project;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, idProj);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						project.setIdProject(rSet.getInt("idProjects"));
						project.setNameProject(rSet.getString("name_project"));
						project.setCompany(CompanyDAO.getCompany(rSet.getInt("id_company")));
						project.setCustomer(CustomerDAO.getCustomer(rSet.getInt("id_customer")));
						project.setCost(rSet.getInt("cost"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getProject. SQL request isn't correct.");
				e.printStackTrace();
				return project;
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
				System.err.println("Method getProject. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return project;
	}
	
	public static void addProject(Project project) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		Project checkProj = getProject(project.getNameProject());
		try {
			if(checkProj.getNameProject().equals(project.getNameProject())) {
				System.err.println("Project already exist");
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
				String sql = ProjectService.queryBuilderAdd(project);
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
				System.err.println("Method addProject. SQL request isn't correct.");
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
				System.err.println("Method addProject. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	public static Project removeProject(String nameProj) throws Exception{
		Project project = getProject(nameProj);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM projects where name_project=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeProject. Connection is not established!");
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameProj);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(project != null){
						statement.executeUpdate("DELETE FROM projects WHERE name_project='" + nameProj + "';");
					}  else{
					return null;
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeProject. SQL request isn't correct.");
				return project;
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
				System.err.println("Method removeCustomer. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return project;
	}
	
	
/*	private static int getIdProj(String nameProj) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idProj = 0;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					nameProj + "';");
			if (rsId == null && !nameProj.equals(null)) {
				Project project = new Project(nameProj);
				ProjectDAO.addProject(project);
				rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
						nameProj + "';");
				while(rsId.next()){ idProj = rsId.getInt("idProjects");}
			}else if (nameProj == null){
				return 0;
			}
			else {
				while(rsId.next()){ idProj = rsId.getInt("idProjects");}
			}
		}
		catch (SQLException e) {
			System.err.println("Method getIdProj. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
				System.err.println("Method getIdProj. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return idProj;
	}
	
	
	public static String getNameProj(Integer idProj) throws Exception {
		Connection connection = null;
		Statement statement = null;
		String nameProj = null;
		ResultSet rsName = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsName = statement.executeQuery("Select name_project from projects where idProjects='" +
					idProj + "';");
			while(rsName.next()){ nameProj = rsName.getString("name_project");}
		}
		catch (SQLException e) {
			System.err.println("Method getNameProj. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsName!=null){
					rsName.close();
					}
				if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getNameProj. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return nameProj;
	}
	
	public static int getIdCompAdd(Project project) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idComp = 0;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select id_company from projects where name_project='" +
					project.getNameProject() + "';");
			while(rsId.next()){ idComp = rsId.getInt("id_company");}
		}
		catch (SQLException e) {
			System.err.println("Method getIdComp. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getIdComp. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return idComp;
	}*/
}
