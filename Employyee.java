package bean;

import java.io.Serializable;

public class Employyee implements Serializable{
	private String name;
	private double salary;
	private int age;
    private String address;
	
	
	public Employyee(String name, double salary, int age, String address) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.address = address;
		
	}
	
	
	public Employyee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	

	
	
}
