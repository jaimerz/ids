import java.rmi.*;

public class InfoImpl implements Info_itf {

	private String name;

	public InfoImpl(String name){
		this.name = name;
	}

	public String getName() throws RemoteException{
		return name;
	}
}