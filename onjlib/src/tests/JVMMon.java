package tests;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.management.MBeanConstructorInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;

public class JVMMon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RuntimeMXBean  mxbean = ManagementFactory.getRuntimeMXBean();
		String vendor = mxbean.getVmVendor();
		String version = mxbean.getVmVersion();
		String name = mxbean.getVmName();
		
		System.out.println("name="  + name );
		System.out.println("vendor="  + vendor );
		System.out.println("version="  + version );
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		 

		try {
			
		
			// 	Get a MBean proxy for RuntimeMXBean interface
			RuntimeMXBean proxy = 
				ManagementFactory.newPlatformMXBeanProxy(mbs,
		                                                ManagementFactory.RUNTIME_MXBEAN_NAME,
		                                                RuntimeMXBean.class);
			// Get standard attribute "VmVendor" 
			vendor = proxy.getVmVendor();
			System.out.println("remote vendor="  + vendor );
			String domain = mbs.getDefaultDomain();
			System.out.println("remote domain="  + vendor );
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		while(true) {
			
		}

	}

}
