package com.mts.nrtrde.server;


import java.io.File;
import java.util.Date;


public interface GenerateNRTRDE {

	public File generateTapFile();
	
	public File convertToNRTRDE(File tapFile);
	
	public void sendToHub(File nrtrde);
	
	public File[] generateFiles(String vendor, Date from, Date to);
}
