package onjlib.utils.classfile;

public abstract class UTF8Dict {

	static ConstantPool m_cp;
	public static void setConstantPool(ConstantPool cp)
	{
		m_cp = cp;
	}

	public static String getString(int index)
	{
		String s=null;
		ConstantRef cr =  m_cp.getRef(index-1);

		if(cr instanceof UTF8Ref) {
			s = ((UTF8Ref)cr).getText();
		} else {
			if(cr instanceof StringRef) {
				s = ((StringRef) cr).getText();
			}
		}
		return(s);

	}

}
