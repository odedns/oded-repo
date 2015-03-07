

package onjlib.rmi;

import java.rmi.*;
import java.io.*;

public interface RmiSessionIF extends Remote {
	public void setData(String s) throws RemoteException;
	public String getData() throws RemoteException;
}
