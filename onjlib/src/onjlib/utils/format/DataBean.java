package onjlib.utils.format;

import java.util.*;

public class DataBean {
	Hashtable m_data;
	Hashtable m_formatters;
	Properties m_props;

	public DataBean()
	{
		m_data = new Hashtable();
		m_formatters =  new Hashtable();
	}

	public DataBean(Properties p)
	{
		m_data = new Hashtable();
		m_formatters =  new Hashtable();
		setFormatters(p);
	}

	public void setFormatters(Properties p)
	{
		String name = null;
		String value = null;
		Class c = null;
		FormatterIF fmt = null;

		m_props = p;
		Enumeration e = p.propertyNames();

		while(e.hasMoreElements()) {
			name = (String)e.nextElement();
			value = p.getProperty(name);
			System.out.println("name=" + name + " value=" + value);
			if(!m_formatters.containsKey(value)) {
				try {
					c = Class.forName(value);
					fmt = (FormatterIF) c.newInstance();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				m_formatters.put(value,fmt);
			}
		}

	}

	public void set(String name, Object o)
	{
		m_data.put(name,o);
	}

	public void set(String name, Object l[])
	{
		Vector v = new Vector(l.length);
		for(int i=0; i< l.length;++i) {
			v.addElement(l[i]);
		}
		m_data.put(name,v);
	}


	public Vector getVector(String name)
	{
		return((Vector)m_data.get(name));
	}

	public Object[] getList(String name)
	{
		Vector v = (Vector)m_data.get(name);
		Object list[] = new Object[v.size()];
		Enumeration e = v.elements();
		int i = 0;
		while(e.hasMoreElements()) {
			list[i++] = (Object)e.nextElement();
		}
		return(list);
	}

	public Object get(String name)
	{
		return(m_data.get(name));
	}

	public String[] getFormattedList(String name)
	{
		Object list[] = getList(name);
		String fmtList[] = new String[list.length];

		for(int i=0; i<list.length;++i) {
			fmtList[i] = formatValue(name,list[i]);
		}
		return(fmtList);

	}

	public Vector getFormattedVector(String name)
	{
		Vector v = getVector(name);
		return(v);
	}


	public String getFormatted(String name)
	{
		Object value = m_data.get(name);

		if(null == value) {
			return(null);
		}
		return(formatValue(name,value));
	}

	private String formatValue(String name, Object value)
	{
		String fmtName = m_props.getProperty(name);
		String strValue = null;
		if(null != fmtName) {
			FormatterIF fmt=(FormatterIF)m_formatters.get(fmtName);
			if(null != fmt) {
				strValue = fmt.format(value);
			}
		}
		return(strValue);
	}

	public static void main(String argv[])
	{
		DataBean bean = new DataBean();
		Properties p = new Properties();

		bean.set("name", "Poo");
		bean.set("address", "Givataim");
		bean.set("id", new Integer(100));
		bean.set("date", new Date());
		p.put("name","onjlib.utils.format.AmountFormatter");
		p.put("address","onjlib.utils.format.AmountFormatter");
		p.put("date","onjlib.utils.format.DateFormatter");
		bean.setFormatters(p);
		String s = (String)bean.get("name");
		Integer id = (Integer)bean.get("id");
		System.out.println("name = " + s + " id = " + id);
		String date = bean.getFormatted("date");
		System.out.println("date = " + date);
		Date d = (Date) bean.get("date");
		System.out.println("d = " + d);

	}

}
