package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.CompanyDAO;
import DAO.Connector;
import DAO.ProjectDAO;
import Model.Company;
import Model.Project;

public class ProjectService {

	public ProjectService() {
		// TODO Auto-generated constructor stub
	}
	
	public static Company getIdCompAdd(Project project) throws Exception {
		/*Connection connection = null;
		Statement statement = null;
		ResultSet rsId = null;*/
		Company company = null;
		if (project.getCompany() != null){
			if (project.getCompany().getIdCompany() != null) {
				return project.getCompany();
			}
			return CompanyService.getIdComp(project.getCompany());
		}
		
/*		try {
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
		}*/
		return company;
	}
	
	
	public static boolean checkCompany(Project project, Company company) throws Exception {
		Project checkerTablePr = ProjectDAO.getProject(project.getNameProject());
		Company checkerTableComp = CompanyDAO.getCompany(company.getNameCompany());
		try {
			if(checkerTablePr.getCompany().getIdCompany() == checkerTableComp.getIdCompany()) {
				return true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public static Project getIdProj(Project project) throws Exception {
		Project projectUpd = null;
		if(project.getIdProject() == null){
			ProjectDAO.addProject(project);
			projectUpd = ProjectDAO.getProject(project.getNameProject());
			//idComp = companyUpd.getIdCompany();
		}
	/*	}
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
	}*/
	return projectUpd;
	}
	
	public static String queryBuilderAdd(Project project){
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		int count = 0;
		try {
		if (project.getNameProject() != null) {
			columnDim[count] = "name_project";
			valueDim[count] = "'" + project.getNameProject() + "'";
			count++;
		}
		if (project.getCompany().getIdCompany() != null) {
			columnDim[count] = "id_company";
			valueDim[count] = "'" + project.getCompany().getIdCompany() + "'";
			count++;
		}
		if (project.getCustomer().getId() != null) {
			columnDim[count] = "id_customer";
			valueDim[count] = "'" + project.getCustomer().getId() + "'";
			count++;
		}
		if (project.getCost() != null) {
			columnDim[count] = "cost";
			valueDim[count] = "'" + project.getCost() + "'";
			count++;
		}
		}catch (NullPointerException e) {
			//e.printStackTrace();
		}
		
		String columns = "";
		String values = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				columns += columnDim[i];
				values += valueDim[i];
			}
			else {
				columns += columnDim[i] + ", ";
				values += valueDim[i] + ", ";
			}
		}
		String query = "Insert into projects (" + columns + ") values (" + values + ");";
		return query;
	}
	
	public static String queryBuilderUpdate (Project project) {
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		int count = 0;
		
		if (project.getNameProject() != null) {
			columnDim[count] = "name_project";
			valueDim[count] = "'" + project.getNameProject() + "'";
			count++;
		}
		if (project.getCompany().getIdCompany() != null) {
			columnDim[count] = "id_company";
			valueDim[count] = "'" + project.getCompany().getIdCompany() + "'";
			count++;
		}
		if (project.getCustomer().getId() != null) {
			columnDim[count] = "id_customer";
			valueDim[count] = "'" + project.getCustomer().getId() + "'";
			count++;
		}
		if (project.getCost() != null) {
			columnDim[count] = "cost";
			valueDim[count] = "'" + project.getCost() + "'";
			count++;
		}
		
		String command = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				command += columnDim[i] + "=" + valueDim[i];
			}
			else {
				command += columnDim[i] + "=" + valueDim[i] + ", ";
			}
		}
		
		String query = "update projects set " + command + " where idProjects='" + project.getIdProject() + "';";
		return query;
	}

}
