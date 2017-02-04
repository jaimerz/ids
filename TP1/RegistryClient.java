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
		//try (
				Socket echoSocket = new Socket(hostName, portNumber);
				System.out.println("socket created");
				//PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				//BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
System.out.println("BufferedReader created");
				//New code
				
				ObjectOutputStream objOut = new ObjectOutputStream(echoSocket.getOutputStream());
				objOut.flush();
				ObjectInputStream objIn = new ObjectInputStream(echoSocket.getInputStream());
System.out.println("object streams created");
		//	) {

			String userInput;
			System.out.println("Before menu");
			while (true) {
				
				System.out.print("\033[H\033[2J");
				System.out.println("\nMenu:\n");
				System.out.println("1. Add contact");
				System.out.println("2. Get phone");
				System.out.println("3. Search contact");
				System.out.println("4. Get all contacts");
				System.out.println("5. Exit");
				System.out.print("\nEnter your operation: ");
				
				if((userInput = stdIn.readLine()) != null){

					byte op = Byte.parseByte(userInput);
					if(op>0 && op<5){
						//out.println(userInput);
						objOut.writeObject(userInput);
						objOut.flush();
					}
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
							System.out.print("\nEnter contact name: ");
							name = stdIn.readLine();
							System.out.print("Enter contact phone: ");
							String phone = stdIn.readLine();
							person = new Person(name, phone);
							objOut.writeObject(person);
							objOut.flush();
							//System.out.print(in.readLine());
							System.out.print((String)objIn.readObject());
							System.out.print("\n\nPress \'Enter\' to continue...");
							stdIn.readLine();
						break;

						case 2:
							System.out.print("\nEnter contact name: ");
							name = stdIn.readLine();
							//out.println(name);
							objOut.writeObject(name);
							objOut.flush();
							//System.out.print(in.readLine());
							System.out.print((String)objIn.readObject());
							System.out.print("\n\nPress \'Enter\' to continue...");
							stdIn.readLine();
						break;

						case 3:
							System.out.print("\nEnter contact name: ");
							name = stdIn.readLine();
							//out.println(name);
							objOut.writeObject(name);
							objOut.flush();
							person = (Person)objIn.readObject();
							
							if(person != null)
								System.out.println("\n"+person.print());
							else
								System.out.println("\'"+name+"\' does not match any contact.");

							System.out.print("\n\nPress \'Enter\' to continue...");
							stdIn.readLine();
						break;

						case 4:
							list = (Iterable<Person>)objIn.readObject();
							System.out.println();
							if(list == null)
								System.out.println("Contact list is empty!");
							else
								for(Person p : list){
									System.out.println(p.print());
									System.out.println();
								}

							System.out.print("\n\nPress \'Enter\' to continue...");
							stdIn.readLine();
						break;

					}
				}
			}

/*
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
*/	}
}