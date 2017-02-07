import java.rmi.*;

public interface Accounting_itf extends Remote {

	public void numberOfCalls(int number) throws RemoteException;
	public int getNumber() throws RemoteException;
	public String getName() throws RemoteException;
	public void setNumber(int number) throws RemoteException;

}