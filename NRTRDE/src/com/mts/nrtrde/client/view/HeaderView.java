/**
 * 
 */
package com.mts.nrtrde.client.view;


import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.presenter.HeaderPresenter;

/**
 * @author Oded Nissan
 *
 */
public class HeaderView extends LayoutContainer  implements HeaderPresenter.Display{
	DockPanel panel;
	HorizontalPanel linkPanel;
	
	HorizontalPanel titlePanel;
	Anchor logoutLink;
	
	
	public HeaderView()
	{
		logoutLink = new Anchor("Logout",NRTRDE.constants.logoutPage());
	}
	

	public HasClickHandlers getLogoutLink() {
		// TODO Auto-generated method stub
		return(this.logoutLink);
	}


	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		panel = new DockPanel();
		titlePanel = new HorizontalPanel();
		titlePanel.add(new Image(NRTRDE.images.logo()));
		panel.add(titlePanel,DockPanel.WEST);
		linkPanel = new HorizontalPanel();
		
		linkPanel.setSpacing(5);
		linkPanel.add(logoutLink);
		ThemeSelector sel = new ThemeSelector();
		sel.setWidth(125);
		linkPanel.add(sel);
		panel.add(linkPanel,DockPanel.EAST);
		panel.setCellHorizontalAlignment(linkPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		panel.setWidth("100%");
		panel.addStyleName("header-panel");
		add(panel);
		layout();
	}
	

}
