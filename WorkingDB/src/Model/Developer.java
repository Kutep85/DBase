package Model;

import java.util.ArrayList;

public class Developer {
	
	private Integer id;
	private String name;
	private String surname;
	private Project project;
	private Company company;
	private Integer salary;
	private ArrayList<Skill> skills;

	public Developer() {
		// TODO Auto-generated constructor stub
	}
	
	public Developer(String name, String surname, String project, String company, Integer salary) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	public String toString() {
		String str = "Name Developer:  " + name + 
				"\nSurname Developer:  " + surname +
				"\nProject:  " + project.toString() +
				"\nCompany:  " + company.toString() + 
				"\nSalary:  " + salary;
		return str;
	}
}
