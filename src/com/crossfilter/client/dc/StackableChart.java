package com.crossfilter.client.dc;

import com.crossfilter.client.Dimension;
import com.crossfilter.client.Dimension.Reducer;
import com.crossfilter.client.Group;
import com.google.gwt.core.client.JavaScriptObject;

public abstract class StackableChart<T, K> extends CoordinateGridChart<T, K> {

    protected StackableChart() {
        
    }
    public final native StackableChart<T, K> stack(Group<T, K> group) /*-{
		return this.stack(group);
    }-*/;

    public final <V> StackableChart<T, K> stack(Group<T, K> group, Reducer<T, V> func) {
        return stackJs(group, Dimension._getFunction(func));
    }

    private final native StackableChart<T, K> stackJs(Group<T, K> group, JavaScriptObject func) /*-{
		return this.stack(group);
    }-*/;
}
