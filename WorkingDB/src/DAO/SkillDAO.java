package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Company;
import Model.Skill;

public class SkillDAO {

	
	public static Skill getSkill(String lang, Integer idDev) throws Exception{
		Skill skill = new Skill();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM skills where Language=? and idSkills=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getSkills. Connection is not established!");
				return skill;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, lang);
				statement.setInt(2, idDev);
				rSet = statement.executeQuery();
				if(!rSet.equals(null)){
				while(rSet.next()){
						skill.setIdSkill(rSet.getInt("idSkills"));
						skill.setSkill (rSet.getString("Language"));
						skill.setIdDev(rSet.getInt("idDevelopers"));
						}
				}else{
						return null;
				}
			}
			catch (SQLException e) {
				System.err.println("Method getSkill. SQL request isn't correct.");
				e.printStackTrace();
				return skill;
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
				System.err.println("Method getSkill. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return skill;
	}
/*		
	public static void setSkill(Skill skill) throws Exception {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		String sql = "update skills set  Language=?, idDevelopers=? where idSkills='" + skill.getIdSkill() + "';";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setString. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, skill.getSkill());
					prStatement.setInt(2, skill.getIdDev());
				prStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.err.println("Method setSkill. SQL request isn't correct.");
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
				System.err.println("Method getSkill. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	*/
	
	public static void addSkill(Skill skill) throws Exception {
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		Skill checkSkill = getSkill(skill.getSkill(), skill.getIdDev());
		try {
			if(checkSkill.getSkill().equals(skill.getSkill()) &&
					checkSkill.getIdDev().equals(skill.getIdDev())) {
				System.err.println("Skill already exist");
				return;
			}
		} catch (NullPointerException e){
			
		}
		String sql = "Insert into skills (Language,idDevelopers) values (?,?);";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method addSkill. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, skill.getSkill());
					prStatement.setInt(1, skill.getIdDev());
					prStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.err.println("Method addSkill. SQL request isn't correct.");
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
				System.err.println("Method addSkill. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static Skill removeSkill(String lang, Integer idDev) throws Exception{
		Skill skill = getSkill(lang, idDev);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM skills where Language=? and idDevelopers=?;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeSkill. Connection is not established!");
				return skill;
			}
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, lang);
				statement.setInt(2, idDev);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(skill != null){
						statement.executeUpdate("DELETE FROM skills WHERE Language='" + lang + 
								" and idDevelopers=" + idDev + "';");
					}  else{
						return null;
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeSkill. SQL request isn't correct.");
				return skill;
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
				System.err.println("Method removeSkill. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return skill;
	}
}
