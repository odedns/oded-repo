package onjlib.gui;
import java.awt.*;
import java.util.Vector;
import java.util.StringTokenizer;

/* RatioLayout.java -- Layout manager for Java containers
 *
 * This layout manager allows you to specify ratios of x,y,width,height
 * characteristics of the components.  For example,
 *
 * setLayout(new RatioLayout());
 * add("0,0;.45,.5", new Button("OK")); // upper left corner, 45% x 50%
 * add(".75,.75", new Button("QUIT"));  // 75%,75% and use preferred size
 *
 * You may also specify position coordinates as "c", implying "center".
 *
 * add("c,.2", new Button("OK")); // center across, 20% down
 *
 * Terence Parr
 * MageLang Institute, www.MageLang.com
 */
public class RatioLayout implements LayoutManager {

	// track the ratios for each object of form "xratio,yratio;wratio,hratio"
	Vector ratios = new Vector(10);
	// track the components also so we can remove associated modifier
	// if necessary.
	Vector components = new Vector(10);

	public void addLayoutComponent(String r, Component comp) {
	ratios.addElement(r);
	components.addElement(comp);
	}

	public void removeLayoutComponent(Component comp) {
	int i = components.indexOf(comp);
	if ( i!=-1 ) {
		ratios.removeElementAt(i);
		components.removeElementAt(i);
	}
	}

	public Dimension preferredLayoutSize(Container target) {
	return target.getSize();
	}

	public Dimension minimumLayoutSize(Container target) {
	return target.getSize();
	}

	public void layoutContainer(Container target) {
	Insets insets = target.getInsets();
	int ncomponents = target.getComponentCount();
	Dimension d = target.getSize();
	d.width -=  insets.left+insets.right;
	d.height -= insets.top+insets.bottom;
	for (int i = 0 ; i < ncomponents ; i++) {
		Component comp = target.getComponent(i);
		Dimension compDim = comp.getPreferredSize();
		StringTokenizer st =
		new StringTokenizer((String)ratios.elementAt(i), ", \t;");
		float rx,ry;
		String sx = st.nextToken();
		String sy = st.nextToken();
		// Compute ratios for x,y.
		// If a coordinate is "c" then center the component.
		if ( sx.equalsIgnoreCase("c") ) {
		// compute: what is ratio position for left-edge
		// of component when centered?
		double px = (d.width/2.0) - (compDim.width/2.0);
		rx = (float)px/d.width;  // convert to ratio
		}
		else {
		rx = Float.valueOf(sx).floatValue();
		}
		if ( sy.equalsIgnoreCase("c") ) {
		double py = (d.height/2.0) - (compDim.height/2.0);
		ry = (float)py/d.height; // convert to ratio
		}
		else {
		ry = Float.valueOf(sy).floatValue();
		}
		float rw=0;
		float rh=0;
		int w,h;
		if ( st.hasMoreElements() ) {// get width, height if they exist
		rw = Float.valueOf(st.nextToken()).floatValue();
		rh = Float.valueOf(st.nextToken()).floatValue();
		w = (int)(d.width*rw);
		h = (int)(d.height*rh);
		}
		else {
		w = compDim.width;
		h = compDim.height;
		}
		int x = (int)(d.width*rx);
		int y = (int)(d.height*ry);
		comp.setBounds(x+insets.left,y+insets.top,w,h);
	}
	}

	public String toString() {
	return getClass().getName();
	}
}

