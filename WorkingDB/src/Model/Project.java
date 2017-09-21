package Model;

public class Project {

	private Integer idProject;
	private String nameProject;
	private Company company;
	private Customer customer;
	private Integer cost;
	
	public Project() {
		
	}
	
	public Project(String nameProject) {
		// TODO Auto-generated constructor stub
		this.nameProject = nameProject;
	}

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public String toString() {
		String str = "ID: " + idProject + 
				"\nName: " + nameProject +
				"\n";
		return str;
	}

}
