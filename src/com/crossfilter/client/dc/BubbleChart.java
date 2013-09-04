package com.crossfilter.client.dc;

import com.google.gwt.core.client.JavaScriptObject;

public class BubbleChart<T, K> extends CoordinateGridChart<T, K> {

    protected BubbleChart() {
    }

    public static native <T, K> BubbleChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.bubbleChart(cssSelector);
    }-*/;

    public static <T, K> BubbleChart<T, K> create(ChartContainer w) {
        BubbleChart<T, K> chart = create(w.getSelector());
        w.setChart(chart);
        return chart;
    }

    public final native BubbleChart<T, K> r(JavaScriptObject scale) /*-{
		return this.r(scale);
    }-*/;

    public final void radiusValueAccessor(Accessor accessor) {
        radiusValueAccessor(accessor.getFunction());
    }

    private final native BaseChart<T, K> radiusValueAccessor(JavaScriptObject func) /*-{
		return this.radiusValueAccessor(func);
    }-*/;

    public final native BubbleChart<T, K> elasticRadius(boolean elastic) /*-{
		return this.elasticRadius(elastic);
    }-*/;

    public final native BubbleChart<T, K> minRadiusWithLabel(double minRadius) /*-{
		return this.minRadiusWithLabel(minRadius);
    }-*/;

    public final native BubbleChart<T, K> maxBubbleRelativeSize(double relativeSize) /*-{
		return this.maxBubbleRelativeSize(relativeSize);
    }-*/;
}
