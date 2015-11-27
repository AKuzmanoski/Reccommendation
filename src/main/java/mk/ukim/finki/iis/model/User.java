package mk.ukim.finki.iis.model;

public class User {
	
	private String name;
	private String url;
	private String country;
	
	public User(){
		
	}
	public User(String name, String url, String country) {
		super();
		this.name = name;
		this.url = url;
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", url=" + url + ", country=" + country
				+ "]";
	}
	
	
}
