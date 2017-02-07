import java.rmi.*;

public class Hello2Impl implements Hello2 {

	private String message;
	private final int notify_number = 20;

	public Hello2Impl(String s) {
		message = s ;
	}

	public String sayHello(Accounting_itf client) throws RemoteException {
		client.setNumber(client.getNumber() + 1);
		System.out.println("Client name: " + client.getName() + " has made: "+client.getNumber()+" call(s)");
		
		int number = client.getNumber();

		if(number >= notify_number){
			client.numberOfCalls(number);
			return "No more messages for you! hehe xD";
		}

		return message ;
	}
}