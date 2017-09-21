package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.CompanyDAO;
import DAO.Connector;
import Model.Company;

public class CompanyService {

	public static Company getIdComp (Company company) throws Exception {
		/*Connection connection = null;
		Statement statement = null;
		ResultSet rsId = null;
		try {*/
		//Integer idComp = null;
		//	connection = Connector.getConnector();
		//	statement = connection.createStatement();
			//rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
			//		company.getNameCompany() + "';");
		Company companyUpd = null;
			if(company.getIdCompany() == null){
				CompanyDAO.addCompany(company);
				companyUpd = CompanyDAO.getCompany(company.getNameCompany());
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
		return companyUpd;
	}
	
}
