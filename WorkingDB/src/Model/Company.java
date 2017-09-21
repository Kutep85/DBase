package Model;

public class Company {

	private Integer idCompany;
	private String nameCompany;
	
	
	public Company() {
		
	}
	
	public Company(String nameCompany) {
		// TODO Auto-generated constructor stub
		this.nameCompany = nameCompany;
	}

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	public String toString() {
		String str = "ID: " + idCompany + 
				"\nName: " + nameCompany +
				"\n";
		return str;
	}

}
