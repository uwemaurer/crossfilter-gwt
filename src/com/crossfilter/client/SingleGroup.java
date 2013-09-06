package com.crossfilter.client;

import com.google.gwt.core.client.JavaScriptObject;

public class SingleGroup<T, K> extends JavaScriptObject {

    protected SingleGroup() {
    }

    public final native int size() /*-{
		return this.size();
    }-*/;

    public final native SingleGroup<T, K> reduceCount() /*-{
		return this.reduceCount();
    }-*/;

    public final native double getValue() /*-{
		return this.value();
    }-*/;

    public final native int getValueInt() /*-{
		return this.value();
    }-*/;
}
