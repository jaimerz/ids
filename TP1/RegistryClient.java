import java.io.*;
import java.net.*;

public class RegistryClient {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		System.out.println("Trying");
		try (
				Socket echoSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

				//New code
				ObjectInputStream objIn = new ObjectInputStream(echoSocket.getInputStream());
				ObjectOutputStream objOut = new ObjectOutputStream(echoSocket.getOutputStream());

			) {

			String userInput;
			System.out.println("Before menu");
			while (true) {
				
				System.out.println("\nMenu:");
				System.out.println("1. Add contact");
				System.out.println("2. Get phone");
				System.out.println("3. Search contact");
				System.out.println("4. Get all contacts");
				System.out.println("5. Exit");
				System.out.print("Enter your operation: ");
				
				if((userInput = stdIn.readLine()) != null){

					byte op = Byte.parseByte(userInput);
					if(op>0 && op<5)
						out.println(userInput);
					else if(op == 5){
						System.out.println("Exiting...");
						System.exit(0);
					}else{
						System.out.println("Invalid option! Please try again!");
						continue;
					}

					Person person;
					Iterable<Person> list;
					String name;

					switch(op){

						case 1:
							System.out.print("Enter contact name: ");
							name = stdIn.readLine();
							System.out.print("Enter contact phone: ");
							String phone = stdIn.readLine();
							person = new Person(name, phone);
							objOut.writeObject(person);
							System.out.print(in.readLine());
						break;

						case 2:
							System.out.print("Enter contact name: ");
							name = stdIn.readLine();
							out.println(name);
							System.out.print(in.readLine());
						break;

						case 3:
							System.out.print("Enter contact name: ");
							name = stdIn.readLine();
							out.println(name);
							person = (Person)objIn.readObject();
							
							if(person != null)
								System.out.println(person.print());
							else
								System.out.println("\'"+name+"\' does not match any contact.");
						break;

						case 4:
							list = (Iterable<Person>)objIn.readObject();
							if(list == null)
								System.out.println("Contact list is empty!");
							else
								for(Person p : list)
									System.out.println(p.print());
						break;

					}
				}
			}


		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}