package onjlib.cs.cmd;

/**
 * @author Oded Nissan 01/03/2004
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TestCmd {

	public static void main(String[] args) {
		
		HashCmdParams params = new HashCmdParams();
		params.setParam("1","one");
		try {
			CSManager.setUrl("http://localhost:9080/myweb/servlet/onjlib.cs.cmd.CommandServlet");
			HashCmdParams out = (HashCmdParams) CSManager.executeCommand("onjlib.cs.cmd.TestSrvCmd",params);
			System.out.println("got out = " + out.toString());
		} catch(Exception e) {
			e.printStackTrace();	
		}
		
	}
}
