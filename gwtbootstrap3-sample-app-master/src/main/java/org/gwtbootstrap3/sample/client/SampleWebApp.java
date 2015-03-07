package org.gwtbootstrap3.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Joshua Godi
 */
public class SampleWebApp extends Composite implements EntryPoint {
    interface MyUiBinder extends UiBinder<Widget, SampleWebApp> {
    }

    private static MyUiBinder myUiBinder = GWT.create(MyUiBinder.class);

    public void onModuleLoad() {
        RootPanel.get().add(myUiBinder.createAndBindUi(this));
    }
}