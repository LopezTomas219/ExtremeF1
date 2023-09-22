package model;

public class Country {

    private String name ;
    private String nameAbbreviated;

	public Country(String name) {
		super();
		this.name = name;
	}

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameAbbreviated() {
		return nameAbbreviated;
	}
	
	public void setNameAbbreviated(String nameAbbreviated) {
		this.nameAbbreviated = nameAbbreviated;
	}
}
