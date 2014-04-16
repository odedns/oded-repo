/**
 * Copyright (c) 1994-2002 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */
package onjlib.utils;

import java.io.*;

/**
 * A class for executing commands on the OS shell.
 * <p>Copyright (c) 2001
 * @author      Oded Nissan
 * @version 1.0 7/2001
 */

public class CommandShell {

	StringBuffer m_output;
	StringBuffer m_error;

	/**
	 * Empty constructor.
	 */
	public CommandShell()
	{
	}

	public int execShellCmd(String command) throws Exception
	{
		String args[] = new String[3];
		args[0] = "cmd.exe";
		args[1] = "/c";
		args[2] = command;
		return(exec(args));
	}

	/**
	 * Execute an external process with parameters.
	 * @param proc a String specifying the name of the executable
	 * to execute.
	 * @param args a String array of command line parameters
	 * to for the executing process.
	 * @return in the exit value of the executed process.
	 * @throws Exception in case of error.
	 */
	public int execProcess(String proc, String args[]) throws Exception
	{
		String nargs[] = new String[args.length+1];
		nargs[0] = proc;
		System.arraycopy((Object)args,0,(Object)nargs,1,args.length);
		return(exec(nargs));
	}

	/**
	 * Execute an external process.
	 * @param proc a String specifying the name of the executable
	 * to execute.
	 * @return in the exit value of the executed process.
	 * @throws Exception in case of error.
	 */
	public int execProcess(String proc) throws Exception
	{
		String nargs[] = new String[1];
		nargs[0] = proc;
		return(exec(nargs));


	}

	private int exec(String args[]) throws Exception
	{
		Runtime rt =Runtime.getRuntime();
		Process p=rt.exec(args);
		BufferedReader br = new BufferedReader(	new InputStreamReader(p.getInputStream()));
		BufferedReader brErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		m_output = new StringBuffer(250);
		m_error = new StringBuffer(250);
		m_output.append("");
		m_error.append("");
		String line;
		while(null != (line=br.readLine())) {
			m_output.append(line);
		}
		while(null != (line=brErr.readLine())) {
			m_error.append(line);
		}

		p.waitFor();
		br.close();
		return(p.exitValue());

	}

	/**
	 * Get the stderr output of the executed process.
	 * @param String a buffer containing the stderr output.
	 */
	public String getError()
	{
		return(m_error.toString());
	}

	/**
	 * Get the stdout output of the executed process.
	 * @param String a buffer containing the stdout output.
	 */
	public String getOutput()
	{
		return(m_output.toString());
	}

	public static void main(String argv[])
	{
		CommandShell cs = new CommandShell();
		try {
			cs.execShellCmd("dir");
			System.out.println("output = " + cs.getOutput());
			//cs.execProcess("notepad");
			//System.out.println("output = " + cs.getOutput());
			 //cs.execProcess("cmd.exe /c dir");
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}



	}

}
