package onjlib.gui;

import java.awt.*;

/**
 * gui utility class.
 * This is an abstract class that contains misc
 * utility functions.
 */
public abstract class Gutils {

	/**
	 * centers a frame on the screen.
	 * @param f Frame to center.
	 */
	public void center(Frame f)
	{

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation( ( screen.width -  f.getSize().width ) / 2,
				( screen.height - f.getSize().height ) / 2 );

	}

	public void center(Component parent)
	{

		/*
		pack();
		Point p = parent.getLocation();
		Dimension d = parent.getgetSize();
		Dimension s = getgetSize();
		p.translate((d.width - s.width) / 2,(d.height - s.height) / 2);
		setLocation(p);
		*/

	}

}
