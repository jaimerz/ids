import java.rmi.*;

public interface Registry_itf extends Remote {

	public void register(Accounting_itf client) throws RemoteException;
	public Accounting_itf getRegistered(String name) throws RemoteException;

}