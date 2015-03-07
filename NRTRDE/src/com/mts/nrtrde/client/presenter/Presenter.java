package com.mts.nrtrde.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract interface Presenter {
  public void go(final HasWidgets container);
  public void bind();
}
