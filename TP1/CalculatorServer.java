import java.io.*;
import java.net.*;

public class CalculatorServer implements Calculator_itf {

	public int plus(int n1, int n2){
		return n1 + n2;
	}

	public int minus(int n1, int n2){
		return n1 - n2;
	}

	public int divide(int n1, int n2){
		return n1 / n2;
	}

	public int multiply(int n1, int n2){
		return n1 * n2;
	}


	public static void main(String[] args) throws IOException {

		CalculatorServer calculator = new CalculatorServer();

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
		){
			String inputLine;
			while((inputLine = in.readLine()) != null){

				String[] response = inputLine.split(" ");
				int n1, n2;
				char op;

				n1 = Integer.parseInt(response[0]);
				n2 = Integer.parseInt(response[2]);
				op = response[1].charAt(0);


				switch(op){
					case '+':
						out.println( calculator.plus(n1, n2) );
					break;

					case '-':
						out.println( calculator.minus(n1, n2) );
					break;

					case '*':
						out.println( calculator.multiply(n1, n2) );
					break;

					case '/':
						out.println( calculator.divide(n1, n2) );
					break;
				}

				
			}
		}catch(IOException e){
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		

	}
}