/**
 * 
 */
package com.mts.nrtrde.client;

import com.google.gwt.i18n.client.Messages;

/**
 * @author Oded Nissan
 *
 */
public interface NRTRDEMessages  extends Messages{
	
	@DefaultMessage("Current thresholds: volume {0} usage: {1} SMS {2}")
	String alertMsg(int volume,int usage, int sms);

}
