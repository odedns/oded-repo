
package onjlib.utils.format;

import java.util.Date;
import java.text.DateFormat;

public class DateFormatter implements FormatterIF {
	public String format(Object o)
	{
		return(DateFormat.getDateInstance().format(o));
	}
}
