import java.io.*;

public class Person implements Serializable {
	private String name;
	private String phone;
	
	public Person(){

	}

	public Person(String name){
		this.phone = phone;
	}

	public Person(String name, String phone){
		this.name = name;
		this.phone = phone;
	}

	String getName(){
		return this.name;
	}

	String getPhone(){
		return this.phone;
	}

	void setName(String name){
		this.name = name;
	}

	void setPhone(String phone){
		this.phone = phone;
	}

	String print(){
		return "Name: " + this.name + "\n" + "Phone: " + this.phone;
	}
}