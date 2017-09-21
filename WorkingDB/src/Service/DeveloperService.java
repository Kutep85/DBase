package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.CompanyDAO;
import DAO.ProjectDAO;
import Model.Developer;

public class DeveloperService {

	public static String queryBuilderAdd(Developer developer) throws Exception{
		String[] columnDim = new String[5];
		String[] valueDim = new String[5];
		//String idPr = null;
		//String idComp = null;
		//ResultSet rsId = null;
		int count = 0;
		try {
			/*
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					developer.getProject() + "';");
			while(rsId.next()){ idPr = rsId.getString(1);}
			rsId.close();
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					developer.getCompany() + "';");
			while(rsId.next()){ idComp = rsId.getString(1);}
			*/
			if (developer.getName() != null) {
				columnDim[count] = "namedevelopers";
				valueDim[count] = "'" + developer.getName() + "'";
				count++;
			}
			
			if (developer.getSurname() != null) {
				columnDim[count] = "surnamedevelopers";
				valueDim[count] = "'" + developer.getSurname() + "'";
				count++;
			}
			
			if (developer.getProject() != null) {
				if (developer.getCompany() != null && developer.getProject().getCompany() == null) {
					developer.getProject().setCompany(developer.getCompany());
				}
				if(developer.getProject().getCompany().getIdCompany() == null){
					developer.getProject().setCompany(CompanyDAO.getCompany(developer.getCompany().getNameCompany()));
				}
				if(developer.getProject().getIdProject() == null){
					developer.setProject(ProjectService.getIdProj(developer.getProject()));
				}
				if (developer.getCompany() == null) {
					developer.setCompany(ProjectService.getIdCompAdd(developer.getProject()));
				}				
				columnDim[count] = "id_project";
				valueDim[count] = "'" + developer.getProject().getIdProject() + "'";
				count++;
			}
			
			if (developer.getCompany() != null) {
				if (developer.getProject() != null && 
					ProjectService.checkCompany(developer.getProject(), developer.getCompany()) == false) {
					System.err.println("Wrong company for project. Company will be null");
					developer.setCompany(null);
				} else
					if(developer.getCompany().getIdCompany() == null){
					developer.setCompany(CompanyService.getIdComp(developer.getCompany()));
					}
				
				columnDim[count] = "id_comp";
				valueDim[count] = "'" + developer.getCompany().getIdCompany() + "'";
				count++;
			}
			
			if (developer.getSalary() != null) {
				columnDim[count] = "salary";
				valueDim[count] = "'" + developer.getSalary() + "'";
				count++;
			}
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		/*finally{
			try {
				if(rsId!=null){
					rsId.close();
					} 
			}catch (SQLException e) {
					System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}*/
		
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
		String query = "Insert into developers (" + columns + ") values (" + values + ");";
		return query;
	}
	
	
	public static String queryBuilderUpdate (Developer developer) throws SQLException {
		String[] columnDim = new String[5];
		String[] valueDim = new String[5];
		//String idPr = null;
		//String idComp = null;
		//String idDev = null;
		//ResultSet rsId = null;
		int count = 0;
		try {
			/*rsId = statement.executeQuery("Select idDevelopers from developers where nameDevelopers='" +
					developer.getName() + "' and surnameDevelopers='" + developer.getSurname() + "';");
			while(rsId.next()){ idDev = rsId.getString(1);}
			rsId.close();
			
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					developer.getProject() + "';");
			while(rsId.next()){ idPr = rsId.getString(1);}
			rsId.close();
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					developer.getCompany() + "';");
			while(rsId.next()){ idComp = rsId.getString(1);}*/
			
			if (developer.getName() != null) {
				columnDim[count] = "namedevelopers";
				valueDim[count] = "'" + developer.getName() + "'";
				count++;
			}
			if (developer.getSurname() != null) {
				columnDim[count] = "surnamedevelopers";
				valueDim[count] = "'" + developer.getSurname() + "'";
				count++;
			}
			if (developer.getProject() != null) {
				//developer.setProject(rsId.getString(5));
				columnDim[count] = "id_project";
				valueDim[count] = "'" + developer.getProject().getIdProject() + "'";
				count++;
			}
			if (developer.getCompany() != null) {
				columnDim[count] = "id_comp";
				valueDim[count] = "'" + developer.getCompany().getIdCompany() + "'";
				count++;
			}	 
			if (developer.getSalary() != null) {
				columnDim[count] = "salary";
				valueDim[count] = "'" + developer.getSalary() + "'";
				count++;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		/*finally{
			try {
				if(rsId!=null){
					rsId.close();
					} 
			}catch (SQLException e) {
					System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
*/		
		String command = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				command += columnDim[i] + "=" + valueDim[i];
			}
			else {
				command += columnDim[i] + "=" + valueDim[i] + ", ";
			}
		}
		
		String query = "update developers set " + command + " where idDevelopers='" + developer.getId() + "';";
		return query;
	}

}
