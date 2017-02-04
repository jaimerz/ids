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

		//try(
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Socket clientSocket = serverSocket.accept();
			//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			//New code
			ObjectOutputStream objOut = new ObjectOutputStream(clientSocket.getOutputStream());
			objOut.flush();
			ObjectInputStream objIn = new ObjectInputStream(clientSocket.getInputStream());
		//){/
			
			String inputLine;
			//while((inputLine = in.readLine()) != null){
			while((inputLine = (String)objIn.readObject()) != null){
				//out.println(inputLine);
				byte op = Byte.parseByte(inputLine);
				String name;
				String phone;

				switch(op){

						case 1:
							Person p = (Person)objIn.readObject();
							registry.add(p);
							//out.println("Contact added successfully!");
							objOut.writeObject("\nContact added successfully!");
							objOut.flush();
						break;

						case 2:
							//name = in.readLine();
							name = (String)objIn.readObject();
							phone = registry.getPhone(name);

							if(phone != null){
								//out.println("Phone: " + phone);
								objOut.writeObject("\n" + name + "\'s phone is: " + phone);
								objOut.flush();
							}else{
								//out.println("\'"+name+"\' did not match any contacts! Please try again!");
								objOut.writeObject("\n\'"+name+"\' did not match any contacts! Please try again!");
								objOut.flush();
							}	
						break;

						case 3:
							//name = in.readLine();
							name = (String)objIn.readObject();
							objOut.writeObject(registry.search(name));
							objOut.flush();
						break;

						case 4:
							objOut.writeObject(registry.getAll());
							objOut.flush();
						break;

					}

			}
		/*}catch(IOException e){
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		*/

	}
}