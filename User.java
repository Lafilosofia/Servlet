package bean;

import java.io.Serializable;

public class User implements Serializable{
	private String userName;
	private int age;
	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User(String userName, int age) {
		super();
		this.userName = userName;
		this.age = age;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
