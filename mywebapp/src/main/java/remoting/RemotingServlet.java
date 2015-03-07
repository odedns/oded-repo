/**
 * RemotingServlet.java
 * Mar 19, 2008
 */
package remoting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @author Odedn
 *
 */
public class RemotingServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(RemotingServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		log.debug("in doGet");
		// TODO Auto-generated method stub
		ServletOutputStream os = resp.getOutputStream();
		os.println("<p>RemotingServlet doGet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		Object o = null;
		RemoteResponse remoteResp = null;
		log.debug("RemotingServlet doPost");
		
		log.debug("content length : " + req.getContentLength());
		int length = req.getContentLength();
		
		ObjectOutputStream os = new ObjectOutputStream(resp.getOutputStream());		
		ObjectInputStream ois = new ObjectInputStream(req.getInputStream());

		
			if(length > 0) {
				remoteResp = new RemoteResponse();
				try {
					RemoteRequest remoteReq =(RemoteRequest) ois.readObject();
					log.debug("Remote req: " + ToStringBuilder.reflectionToString(remoteReq));
					o = invoke(remoteReq.getRemoteClass(), remoteReq.getMethodName(), remoteReq.getParams());
				} catch(Exception e) {
					e.printStackTrace();
					remoteResp.setException(e);
					os.writeObject(remoteResp);
					os.flush();
					os.close();
					return;
				}					
				log.debug("got response : " + ToStringBuilder.reflectionToString(o));
				remoteResp.setResult(o);
				os.writeObject(remoteResp);
				os.flush();
				os.close();
				
			}
		
	}

	private Class[] convertArgs(ArrayList args)
	{
		Class vc[] = new Class[args.size()];		
		for(int i=0; i < args.size(); ++i){
			Object o = args.get(i);
			vc[i] = o.getClass();
		}
		return(vc);				
	}
	
	/**
	 * invoke a method on the specific object.
	 * @param c
	 * @param methodName
	 * @param args
	 * @return
	 */
	public Object invoke(String className,String methodName, ArrayList args)
		throws Exception
	{
		log.debug("executing method on class :" + className);
		Class vc[] = convertArgs(args);
		Object vo[] = args.toArray();
		Object o = null;
		Method m = null;
		Class c = Class.forName(className);
		try {
			m  = c.getMethod(methodName,vc);
		} catch(NoSuchMethodException ne) {
			log.error(ne);
			m = findMatchingMethod(c, methodName, vc);
		}
		Object obj = c.newInstance();		
		o = m.invoke(obj,vo);
		log.debug("returning o = " + o.toString());
		return(o);
	}

	@Override
	public void init() throws ServletException
	{
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		
	}

	private Method findMatchingMethod(Class cls, String methodName,
				Class[] params) throws NoSuchMethodException {
			Method[] declaredMethods = cls.getDeclaredMethods();
			for (int i = 0; i < declaredMethods.length; i++) {
				Method method = declaredMethods[i];
				if (!method.getName().equals(methodName)) {
					continue;
				}
				if (checkMethodMatch(method, params)) {
					return method;
				}
			}

			throw new NoSuchMethodException("No method " + cls.getName() + "#"
					+ methodName + "() was found " + "with the given arguments.");
		}

		/**
		 * This method checks to see if the given method's parameters are assignable
		 * from the given arguments.
		 * 
		 * @author nadavw
		 * @param method
		 *            method to examine
		 * @param args
		 *            arguments to match
		 * @return true if the method matches, false if it doesn't
		 */
		private boolean checkMethodMatch(Method method, Class[] params) {
			Class[] parameterTypes = method.getParameterTypes();

			// if no arguments are taken, return true.
			if (params == null && parameterTypes.length == 0) {
				return true;
			}

			if ((params == null) && (parameterTypes.length > 0)) {
				return false;
			}
			// ensure the number of parameters matches .
			if (params.length != parameterTypes.length) {
				log.debug("in method=" + method.getName() + " params.length="
						+ params.length + "parameterTypes.length="
						+ parameterTypes.length);
				return false;
			}

			for (int i = 0; i < parameterTypes.length; i++) {
				Class paramType = parameterTypes[i];
			
				// case the argument is null, continue to the next argument
				if (params[i] != null) {
					// //check if the argument is primitive
					// if argType.
					if (!ClassUtils.isAssignable(paramType, params[i])) {
						log.debug(method.getName()
								+ "() found incompatible at argument #" + (i + 1));
						return false;
					}
				}

			}
			return true;
		}

}
