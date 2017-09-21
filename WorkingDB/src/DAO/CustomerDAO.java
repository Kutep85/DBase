package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Customer;
import Model.Developer;
import Model.Project;
import Service.DeveloperService;

public class CustomerDAO {

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Customer getCustomer(String nameCustomer) throws Exception {
		Customer customer = new Customer();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM customers where name_customer=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getCustomer. Connection is not established!");
				return customer;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameCustomer);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						customer.setId(rSet.getInt("idCustomers"));
						customer.setName(rSet.getString("name_customer"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getProject. SQL request isn't correct.");
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
				System.err.println("Method getProject. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return customer;
	}
	
	public static Customer getCustomer(Integer id) throws Exception {
		Customer customer = new Customer();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM customers where idCustomers=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getCustomer. Connection is not established!");
				return customer;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, id);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						customer.setId(rSet.getInt("idCustomers"));
						customer.setName(rSet.getString("name_customer"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getCustomer. SQL request isn't correct.");
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
				System.err.println("Method getProject. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return customer;
	}
	
	public static void setCustomer(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "update customers set  name_customer=?" + customer.getId() + "';";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setDeveloper. Connection is not established!");
			}
			try {
				//statement = connection.createStatement();
				//String sql = DeveloperService.queryBuilderUpdate(developer);
				statement = connection.prepareStatement(sql);
					statement.setString(1, customer.getName());
				statement.executeUpdate();
				
				statement.executeUpdate(sql);
			}
			catch (SQLException e) {
				System.err.println("Method setCustomer. SQL request isn't correct.");
			}
		}
		finally{
			try {
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
	
	public static void addCustomer(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "Insert into customers (name_customer)"
				+ " values (?);";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method addCustomer. Connection is not established!");
			}
			try {
				statement = connection.prepareStatement(sql);
					statement.setString(1, customer.getName());
			}
			catch (SQLException e) {
				System.err.println("Method addCustomer. SQL request isn't correct.");
				e.printStackTrace();
			}
		}
		finally{
			try {
				if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method addCustomer. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	public static Customer removeCustomer(String nameCust) throws Exception{
		Customer customer = getCustomer(nameCust);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM customers where name_customer=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeCustomer. Connection is not established!");
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, nameCust);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(customer != null){
						statement.executeUpdate("DELETE FROM customers WHERE name_customer='" + nameCust + "';");
					}  else{
					return null;
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeCustomer. SQL request isn't correct.");
				return customer;
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
		return customer;
	}
	
}
