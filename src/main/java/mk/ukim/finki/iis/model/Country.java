package mk.ukim.finki.iis.model;

public class Country {
	
	private Long id;
	private String countryCode;
	private String name;
	
	public Country(){
		
	}
	public Country(Long id, String countryCode, String name) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.name = name;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	

}
