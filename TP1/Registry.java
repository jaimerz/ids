import java.util.*;
import java.io.*;

public class Registry implements Registry_itf, Serializable {
	
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


	public static void main(String[] args) throws IOException {
		Person p1 = new Person("Amela", "0123");
		Person p2 = new Person("Jaime", "2345");
		Registry pr = new Registry();

		pr.add(p1);
		pr.add(p2);

		for(Person p : pr.getAll()){
			System.out.println("Name: " + p.getName());
			System.out.println("Phone: \n" + p.getPhone());
		}

		System.out.println("Search 1, Name: " + pr.search("jaime").getName());
		System.out.println("Search 1, Number: " + pr.search("jaime").getPhone());

		/*OutputStream out = new OutputStream();
		ObjectOutputSteam oos = new ObjectOutputSteam(out);

		oos.writeObject(pr);

		oos.close();

		InputStream in = new InputStream();
		ObjectInputStream ois = new ObjectInputStream(in);

		Registry r = (Registry)in.readObject();

		for(Person p : r.getAll()){
			System.out.println("Name: " + p.getName());
			System.out.println("Phone: \n" + p.getPhone());
		}

		ois.close();
		*/
	}
}

