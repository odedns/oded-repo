
import java.io.*;
import java.net.*;
import java.util.HashMap;



class URLTest2
{


	private static void testGet()
		throws Exception
	{
    	    URL url = new URL("http://localhost:9080/myweb/servlet/CommandServlet?a1=1&a2=2");
	    	HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
	    	
	    	/*
        	urlconn.setDoOutput(true);
        	urlconn.setDoInput(true);
    	    urlconn.setAllowUserInteraction(false);
	        urlconn.setUseCaches(false);
        	urlconn.setRequestMethod("GET");
            urlconn.setRequestProperty("foo","bar");
            urlconn.setRequestProperty ("Content-Type", "text/html");            
            */
            System.out.println("connected ...");
/*            
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(urlconn.getOutputStream()));
			pw.print("a1=1&b1=2");
			pw.close();
*/			                                 
            InputStream inputstream = urlconn.getInputStream();
            BufferedReader br  = new BufferedReader(new InputStreamReader(inputstream));
            String s;
            while((s = br.readLine()) != null)
                System.out.println(s);                                
            br.close();		
			urlconn.disconnect();		
	}
	
	
	private static Object sendObject(Object o)
		throws Exception
	{
	        URL url = new URL("http://localhost:9080/myweb/servlet/CommandServlet");
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setDoOutput(true);
            urlconn.setAllowUserInteraction(false);
            urlconn.setUseCaches(false);
            urlconn.setRequestMethod("POST");
            urlconn.setRequestProperty("foo","bar");
            urlconn.setRequestProperty ("Content-Type", "application/x-java-serialized-object");
            
            System.out.println("connected ...");
            
            ObjectOutputStream os = new ObjectOutputStream(urlconn.getOutputStream());
            os.writeObject(o);
            os.flush();
            os.close();
    
    
    		ObjectInputStream is = new ObjectInputStream(urlconn.getInputStream());                               
    		Object o2 = is.readObject();
    		is.close();
    		return(o2);
	}
	
    public static void main(String args[])
    {
        try
        {
//        	testGet();
			HashMap hm = new HashMap();
			hm.put("1","one");
			hm.put("2","two");
			hm = (HashMap) sendObject(hm);
			
			System.out.println("got back: " + hm.toString());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

}
