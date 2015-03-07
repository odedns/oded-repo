package com.mts.nrtrde.server;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.Date;


public interface CollectNRTRDE {

	public File[] getFilesFromHub() throws SocketException, IOException;
	
	public File[] getFilesFromHub(String vendor, Date from, Date to) throws SocketException, IOException;
}
