package Model;

public class Customer {
	
	private Integer id;
	private String name;

	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
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

}
