import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class HelloClient implements Info_itf {

	private String name;

	public HelloClient(String name){
		this.name = name;
	}

	public String getName() throws RemoteException {
		return name;
	}

	public static void main(String [] args) {
		try {
			
			if (args.length < 2) {
				System.out.println("Usage: java HelloClient <rmiregistry host>");
				return;
			}

			String host = args[0];

			// Get remote object reference
			Registry registry = LocateRegistry.getRegistry(host);
			Hello h = (Hello) registry.lookup("HelloService");

			// Remote method invocation
			HelloClient client = new HelloClient(args[1]);
			Info_itf c_stub = (Info_itf) UnicastRemoteObject.exportObject(client, 0);

			String res = h.sayHello(c_stub);
			System.out.println(res);

		} catch (Exception e) {
			System.err.println("Error on client: " + e) ;
		}
	}
}