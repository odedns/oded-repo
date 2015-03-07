/**
 * File: ClientAsynch.java
 * Date: Mar 5, 2014
 * Author: I070659
 */
package test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author I070659
 *
 */
public class ClientAsynch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("client calling server...");
		synchClient();
		asynchClient();

	}
	
	
	public static void synchClient()
	{
		System.out.println("synchClient calling server");
		String s = callServer();
		System.out.println("got server response s: " + s);
		System.out.println("done....");
		
	}
	
	
	public static void asynchClient() 
	{
		System.out.println("asynchClient calling server");
		ExecutorService executer = Executors.newSingleThreadExecutor();
		Future<String> future = executer.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				String s = callServer();				
				return s;
			}
		});
		
		System.out.println("after asynchCall...");
		String s = "old ";
		try {
			s = future.get();
			System.out.println("after future.get s= " + s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("got server response s: " + s);
	}

	
	public static String callServer() {
		
		System.out.println("server called....");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return("server response");
		
	}
}
