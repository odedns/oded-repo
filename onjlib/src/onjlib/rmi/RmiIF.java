package onjlib.rmi;
/*
 *  RmiIF.java
 *
 *  RmiIF interface is a Remote interface that
 *  gets implemented by the remote object, in
 *  this case the Hello7Server.
 */


import java.rmi.*;
import java.util.Date;

public interface RmiIF extends Remote
{
	String printHello() throws RemoteException;
	Date getTime() throws RemoteException;
	ClientSession connect() throws RemoteException;
	String connectURL() throws RemoteException;
}
