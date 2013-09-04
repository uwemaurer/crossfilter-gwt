package com.crossfilter.client.dc;


public class LineChart<T, K> extends StackableChart<T, K> {

    protected LineChart() {
    }

    public static native <T, K> LineChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.lineChart(cssSelector);
    }-*/;

    public static <T, K> LineChart<T, K> create(ChartContainer w) {
        LineChart<T, K> chart = create(w.getSelector());
        w.setChart(chart);
        return chart;
    }

}
