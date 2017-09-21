package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Company;

public class CompanyDAO {

	
	public static Company getCompany(String nameComp) throws Exception{
		Company company = new Company();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM companies where Namecompany=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getCompany. Connection is not established!");
				return company;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameComp);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						company.setIdCompany(rSet.getInt("idCompanies"));
						company.setNameCompany (rSet.getString("Namecompany"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				e.printStackTrace();
				return company;
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
				System.err.println("Method getCompany. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return company;
	}
	
	public static Company getCompany(Integer idComp) throws Exception{
		Company company = new Company();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM companies where idCompanies=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getCompany. Connection is not established!");
				return company;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, idComp);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						company.setIdCompany(rSet.getInt("idCompanies"));
						company.setNameCompany (rSet.getString("Namecompany"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				e.printStackTrace();
				return company;
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
				System.err.println("Method getCompany. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return company;
	}
	
	
	public static void setCompany(Company company) throws Exception {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		String sql = "update developers set  Namecompany=? where idCompanies='" + company.getIdCompany() + "';";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setCompany. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, company.getNameCompany());
				prStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.err.println("Method setCompany. SQL request isn't correct.");
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(prStatement!=null){
			    	prStatement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getCompany. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static void addCompany(Company company) throws Exception {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		Company checkComp = getCompany(company.getNameCompany());
		try {
			if(checkComp.getNameCompany().equals(company.getNameCompany())) {
				System.err.println("Company already exist");
				return;
			}
		} catch (NullPointerException e){
			
		}
		String sql = "Insert into companies (Namecompany) values (?);";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method Company. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, company.getNameCompany());
					prStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.err.println("Method addCompany. SQL request isn't correct.");
				e.printStackTrace();
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(prStatement!=null){
			    	prStatement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method addCompany. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static Company removeCompany(String nameComp) throws Exception{
		Company company = getCompany(nameComp);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM companies where Namecompany=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeCompany. Connection is not established!");
				return company;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameComp);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(company != null){
						statement.executeUpdate("DELETE FROM companies WHERE Namecompany='" + nameComp + "';");
					}  else{
						return null;
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeCompany. SQL request isn't correct.");
				return company;
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
				System.err.println("Method deleteCompany. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return company;
	}
	
	
	public static Integer getIdComp(Company company) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idComp = null;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					company.getNameCompany() + "';");
			addCompany(company);
			Company companyUpd = getCompany(company.getNameCompany());
			idComp = companyUpd.getIdCompany();
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
	}
	
	
	public static String getNameComp(Integer idComp) throws Exception {
		Connection connection = null;
		Statement statement = null;
		String nameComp = null;
		ResultSet rsName = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsName = statement.executeQuery("Select Namecompany from companies where idCompanies='" +
					idComp + "';");
			while(rsName.next()){ nameComp = rsName.getString("Namecompany");}
		}
		catch (SQLException e) {
			System.err.println("Method getNameComp. SQL request isn't correct.");
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
					System.err.println("Method getNameComp. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return nameComp;
	}
}
