
package com.mts.nrtrde.server;

import java.io.File;
import java.util.Date;


public class GenerateNRTRDEStub implements GenerateNRTRDE{

	@Override
	public File generateTapFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File convertToNRTRDE(File tapFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendToHub(File nrtrde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File[] generateFiles(String vendor, Date from, Date to) {
		File dir = new File("C:\\temp");
		return dir.listFiles();
	}

}
