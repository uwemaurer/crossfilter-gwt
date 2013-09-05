package com.crossfilter.client.dc;


public class LineChart<T, K> extends StackableChart<T, K> {

    protected LineChart() {
    }
    
    public static final <T, K> LineChart<T, K> create() {
        return create(getNextId());
    }
    
    public static native <T, K> LineChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.lineChart(cssSelector);
    }-*/;

    
}
