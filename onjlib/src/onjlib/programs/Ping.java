package onjlib.programs;

import java.net.*;

public class Ping {

	public static void main(String argv[])
	{
		InetAddress inet = null;

		if(argv.length < 1) {
			System.out.println("no hostname given");
			System.exit(1);
		}
		try {
			inet = InetAddress.getByName(argv[0]);
		} catch(UnknownHostException ue) {
			System.err.println("Cannot resolve: " + argv[0]);
			System.exit(2);
		}
		System.out.println("Pinging: " + inet.toString());

		try {
			Socket s = new Socket(argv[0],7);
			s.close();
			System.out.println(argv[0] + " is alive !");
		} catch (Exception e) {
			System.err.println("Cannot connect to: " + argv[0]);
			System.err.println("Error: " + e);
		}


	}

}
