import java.rmi.*;

public class AccountingImpl implements Accounting_itf {

	private String name;
	private int number;

	public AccountingImpl(String name){
		this.name = name;
	}

	public int getNumber() throws RemoteException {
		return this.number;
	}

	public String getName() throws RemoteException {
		return this.name;
	}

	public void setNumber(int number) throws RemoteException {
		this.number = number;
	}

	public void numberOfCalls(int number) throws RemoteException {
		System.out.println("You reached " + number + " number (limit) of calls");
	}
}