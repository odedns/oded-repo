package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.Slider;

public class XSlider extends Slider {
	
	public interface TipRenderer{
		String format(Slider slider, int value);
	}

	protected TipRenderer tipRenderer;
	
	public XSlider() {
		super();
		
		tipRenderer = new TipRenderer() {			
			@Override
			public String format(Slider slider, int value) {
				return Format.substitute(getMessage(), value);
			}
		};
	}
	
	public void setTipRenderer(TipRenderer renderer){
		tipRenderer = renderer;
	}
	
	protected String onFormatValue(int value) {
		return tipRenderer.format(this, value);
	}
}
