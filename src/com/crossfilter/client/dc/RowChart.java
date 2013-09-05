package com.crossfilter.client.dc;


public class RowChart<T, K> extends BaseChart<T, K> {

    protected RowChart() {
    }
    
    public static final <T, K> RowChart<T, K> create() {
        return create(getNextId());
    }
    
    public static native <T, K> RowChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.rowChart(cssSelector);
    }-*/;
    
    // .colors(['#3182bd', '#6baed6', '#9ecae1', '#c6dbef', '#dadaeb'])
    // // (optional) set gap between rows, default is 5
    // gap(7)
    // // (optional) set x offset for labels, default is 10
    // labelOffSetX(5)
    // // (optional) set y offset for labels, default is 15
    // labelOffSetY(10)
    // // (optional) whether chart should render labels, :default = true
    // .renderLabel(true)
    // // (optional) by default pie chart will use group.key and group.value as
    // its title
    // // you can overwrite it with a closure
    // .title(function(d) { return d.data.key + "(" + Math.floor(d.data.value /
    // all.value() * 100) + "%)"; })
    // // (optional) whether chart should render titles, :default = false
    // .renderTitle(true);
    // // (optional) specify the number of ticks for the X axis
    // .xAxis().ticks(4);
}
