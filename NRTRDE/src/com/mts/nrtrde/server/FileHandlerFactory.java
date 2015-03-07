/**
 * 
 */
package com.mts.nrtrde.server;


public class FileHandlerFactory {

	public static GenerateNRTRDE createGenerateHandler(){
		return new GenerateNRTRDEStub();
	}
	
	public static CollectNRTRDE createCollectorHandler(){
		return new CollectNRTRDEStub();
	}
}

