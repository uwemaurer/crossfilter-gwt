package com.crossfilter.client.dc;



public class BarChart<T, K> extends StackableChart<T, K> {

    protected BarChart() {
    }

    public static final <T, K> BarChart<T, K> create() {
        return create(getNextId());
    }
    
    public static native <T, K> BarChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.barChart(cssSelector);
    }-*/;
    
    public final native BarChart<T, K> centerBar(boolean center) /*-{
		return this.centerBar(center);
    }-*/;
    
    public final native BarChart<T, K> gap(int gapPx) /*-{
		return this.gap(gapPx);
    }-*/;
}
