import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class HelloClient {

	public static void main(String [] args) {
		try {
			
			if (args.length < 3) {
				System.out.println("Usage: java HelloClient <rmiregistry host> <service>");
				return;
			}

			String host = args[0];
			String name = args[1];
			String service = args[2];

			Registry_itf r;
			Accounting_itf a_stub;

			// Get remote object reference
			Registry registry = LocateRegistry.getRegistry(host);
			
			switch(service){
				case "HelloService":
					Hello h = (Hello) registry.lookup("HelloService");
			
					// Remote method invocation
					InfoImpl info = new InfoImpl(name);
					Info_itf i_stub = (Info_itf) UnicastRemoteObject.exportObject(info, 0);

					String res = h.sayHello(i_stub);
					System.out.println(res);

				break;

				case "RegistryService":

					r = (Registry_itf) registry.lookup("RegistryService");

					AccountingImpl acc = new AccountingImpl(name);
					a_stub = (Accounting_itf) UnicastRemoteObject.exportObject(acc, 0);

					r.register(a_stub);
					System.out.println("Client has been registered");

				break;

				case "Hello2Service":

					r = (Registry_itf) registry.lookup("RegistryService");

					a_stub = r.getRegistered(name);
					
					if(a_stub != null){
						
						Hello2 h2 = (Hello2) registry.lookup("Hello2Service");

						String res2 = h2.sayHello(a_stub);
						System.out.println(res2);

					}else{
						System.out.println("Client '" + name + "' is not registered. Please register first. Or not please, just go register!" );
					}

				break;
			}

			/*
			Hello h = (Hello) registry.lookup("HelloService");
			
			// Remote method invocation
			InfoImpl info = new InfoImpl(name);
			Info_itf i_stub = (Info_itf) UnicastRemoteObject.exportObject(info, 0);

			String res = h.sayHello(i_stub);
			System.out.println(res);
			*/

			/********************************************************************/

			/*
			Registry_itf r = (Registry_itf) registry.lookup("RegistryService");

			AccountingImpl acc = new AccountingImpl(name);
			Accounting_itf a_stub = (Accounting_itf) UnicastRemoteObject.exportObject(acc, 0);

			r.register(a_stub);
			*/

			/********************************************************************/

			/*
			Hello2 h2 = (Hello2) registry.lookup("Hello2Service");

			String res2 = h2.sayHello(a_stub);
			System.out.println(res2);

			for(int i=0; i<25; i++)
				System.out.println(h2.sayHello(a_stub));
			*/

		} catch (Exception e) {
			System.err.println("Error on client: " + e) ;
		}
	}
}