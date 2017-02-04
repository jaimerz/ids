import java.io.*;
import java.net.*;

public class RegistryServer{
	public static void main(String[] args) throws Exception {

		Registry registry = new Registry();

		if(args.length != 1){
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try(
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//New code
			ObjectInputStream objIn = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream objOut = new ObjectOutputStream(clientSocket.getOutputStream());
		){
			
			String inputLine;
			while((inputLine = in.readLine()) != null){
				//out.println(inputLine);
				byte op = Byte.parseByte(inputLine);
				String name;
				String phone;

				switch(op){

						case 1:
							Person p = (Person)objIn.readObject();
							registry.add(p);
							out.println("Contact added successfully!");
						break;

						case 2:
							name = in.readLine();
							phone = registry.getPhone(name);

							if(phone != null)
								out.println("Phone: " + phone);
							else
								out.println("\'"+name+"\' did not match any contacts! Please try again!");
						break;

						case 3:
							name = in.readLine();
							objOut.writeObject(registry.search(name));
						break;

						case 4:
							objOut.writeObject(registry.getAll());
						break;

					}

			}
		}catch(IOException e){
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		

	}
}