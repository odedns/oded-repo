

package onjlib.net;

class ProtocolClientException extends Exception {
	static final int ERR_INVALIDCODE= -10;
	static final int ERR_IOERROR	= -20;
	static final String ERR_INVALIDCODE_MSG = "invalid return code";
	static final String ERR_IOERROR_MSG = "IO error";
	int m_errCode;

	public ProtocolClientException(int code, String msg)
	{
		super(msg);
		m_errCode = code;
	}

	ProtocolClientException(String msg)
	{
		super(msg);
	}

	public int getErrorCode()
	{
		return(m_errCode);
	}
}
