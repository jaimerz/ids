import java.rmi.*;

public class HelloImpl implements Hello {

	private String message;

	public HelloImpl(String s) {
		message = s ;
	}

	public String sayHello(String clientName) throws RemoteException {
		System.out.println("Client name: " + clientName);
		return message ;
	}
}