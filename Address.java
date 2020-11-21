package bean;

import java.io.Serializable;

public class Address implements Serializable{
	private String city;
	private String area;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(String city, String area) {
		super();
		this.city = city;
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", area=" + area + "]";
	}
}
