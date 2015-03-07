
package com.mts.nrtrde.server;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.Date;


public class CollectNRTRDEStub implements CollectNRTRDE{

	@Override
	public File[] getFilesFromHub() throws SocketException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File[] getFilesFromHub(String vendor, Date from, Date to)
			throws SocketException, IOException {
		File dir = new File("C:\\tmp\\truetype");
		return dir.listFiles();
	}

}
