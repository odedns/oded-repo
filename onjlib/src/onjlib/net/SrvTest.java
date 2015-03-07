package onjlib.net;
import java.net.*;
import java.io.*;


public class SrvTest {

	public static void main(String argv[])
	{

		try {
			ServerSocket server = new ServerSocket(7707);
			Socket sock= server.accept();
			EncryptedSocket esock = new EncryptedSocket(sock);
			DataInputStream dis = new DataInputStream(esock.getInputStream());
			DataOutputStream dos = new DataOutputStream(esock.getOutputStream());

			String s;
			int cnt=0;
			while(true) {
				s = dis.readLine();
				System.out.println("got s = " + s);
				s = s + " " + cnt + "\r\n";
				System.out.println("sending: " + s);
				dos.writeBytes(s);
				dos.flush();

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

