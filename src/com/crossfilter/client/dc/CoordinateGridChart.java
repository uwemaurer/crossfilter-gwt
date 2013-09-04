package com.crossfilter.client.dc;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class CoordinateGridChart<T, K> extends ColorChart<T, K> {

    protected CoordinateGridChart() {
    }
    
    
    // TODO type
    public final native CoordinateGridChart<T, K> x(JavaScriptObject domain) /*-{
        return this.x(domain);
    }-*/;
    

    // TODO type
    public final native CoordinateGridChart<T, K> xUnits(JavaScriptObject units) /*-{
        return this.xUnits(units);
    }-*/;
    
    // TODO type
    public final native CoordinateGridChart<T, K> y(JavaScriptObject domain) /*-{
        return this.y(domain);
    }-*/;
    
    // TODO type
    public final native JavaScriptObject yAxis() /*-{
        return this.yAxis();
    }-*/;
    
    public final native JavaScriptObject yAxisTickFormat(String format) /*-{
        this.yAxis().tickFormat($wnd.d3.format(format));
        return this;
    }-*/;
    
    // TODO type
    public final native CoordinateGridChart<T, K> round(JavaScriptObject round) /*-{
        return this.round(round);
    }-*/;
    
    public final native CoordinateGridChart<T, K> elasticX(boolean elastic) /*-{
        return this.elasticX(elastic);
    }-*/;
    
    public final native CoordinateGridChart<T, K> elasticY(boolean elastic) /*-{
		return this.elasticY(elastic);
    }-*/;

    public final native CoordinateGridChart<T, K> xAxisPadding(int padding) /*-{
		return this.xAxisPadding(padding);
    }-*/;

    
    public final native CoordinateGridChart<T, K> renderHorizontalGridLines(boolean lines) /*-{
		return this.renderHorizontalGridLines(lines);
    }-*/;
    
    public final native CoordinateGridChart<T, K> renderVerticalGridLines(boolean lines) /*-{
        return this.renderVerticalGridLines(lines);
    }-*/;
    
    
}
