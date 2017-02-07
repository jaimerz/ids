import java.rmi.*;
import java.util.*;

public class RegistryImpl implements Registry_itf {

	private HashMap<String, Accounting_itf> list;

	public RegistryImpl() {
		list = new HashMap<String, Accounting_itf>();
	}

	public void register(Accounting_itf client) throws RemoteException {
		list.put(client.getName(), client);
	}

	public Accounting_itf getRegistered(String name) throws RemoteException {
		return list.get(name);
	}
}