package com.crossfilter.client.dc;

import com.google.gwt.core.client.JavaScriptObject;

public class RowChart<T, K> extends BaseChart<T, K> {

    protected RowChart() {
    }

    public static final <T, K> RowChart<T, K> create() {
        return create(getNextId());
    }

    public static native <T, K> RowChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.rowChart(cssSelector);
    }-*/;

    public final native RowChart<T, K> elasticX(boolean elastic) /*-{
		return this.elasticX(elastic);
    }-*/;

    public final native RowChart<T, K> gap(int gapPx) /*-{
		return this.gap(gapPx);
    }-*/;

    public final native RowChart<T, K> labelOffSetX(boolean elastic) /*-{
		return this.labelOffSetX(elastic);
    }-*/;

    public final native RowChart<T, K> labelOffSetY(boolean elastic) /*-{
		return this.labelOffSetY(elastic);
    }-*/;
    
    public final native RowChart<T, K> rowsCap(int cap) /*-{
        return this.rowsCap(cap);
    }-*/;
    
    public final native RowChart<T, K> removeRowsCap() /*-{
        return this.rowsCap(Infinity);
    }-*/;
    
    public final native RowChart<T, K> rowOther(boolean other) /*-{
        return this.rowOther(other);
    }-*/;
    
    public final void othersLabel(StringAccessor accessor) {
        othersLabel(accessor.getFunction());
    }

    private final native RowChart<T, K> othersLabel(JavaScriptObject func) /*-{
        return this.othersLabel(func);
    }-*/;
}
