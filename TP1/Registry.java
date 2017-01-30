import java.util.*;

public class Registry implements Registry_itf{
	
	private HashMap<String, Person> list;

	public Registry(){
		list = new HashMap<String, Person>();
	}

	public void add(Person p){
		list.put(p.getName(), p);
	}

	public Iterable<Person> getAll(){
		return list.values();
	}
	
	public String getPhone(String name){
		return search(name).getPhone();
	}
	
	public Person search(String name){
		return list.get(name);
	}


	public static void main(String[] args){
		Person p1 = new Person("Amela", "0123");
		Person p2 = new Person("Jaime", "2345");
		Registry pr = new Registry();

		pr.add(p1);
		pr.add(p2);

		for(Person p : pr.getAll()){
			System.out.println("Name: " + p.getName());
			System.out.println("Phone: \n" + p.getPhone());
		}
	}
}

