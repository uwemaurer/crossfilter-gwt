package com.crossfilter.client.dc;


public class BarChart<T, K> extends StackableChart<T, K> {

    protected BarChart() {
    }

    public static native <T, K> BarChart<T, K> create(String cssSelector) /*-{
		return $wnd.dc.barChart(cssSelector);
    }-*/;

    public static <T, K> BarChart<T, K> create(ChartContainer w) {
        BarChart<T, K> chart = create(w.getSelector());
        w.setChart(chart);
        return chart;
    }
    
   
    
    public final native BarChart<T, K> centerBar(boolean center) /*-{
		return this.centerBar(center);
    }-*/;
    
    public final native BarChart<T, K> barGap(int gapPx) /*-{
		return this.barGap(gapPx);
    }-*/;
    
   
   
//    // (optional) add stacked group and custom value retriever
//    .stack(monthlyMoveGroup, function(d){return d.value;})
//    // (optional) you can add multiple stacked group with or without custom value retriever
//    // if no custom retriever provided base chart's value retriever will be used
//    .stack(monthlyMoveGroup)
//    // (optional) whether this chart should generate user interactive brush to allow range
//    // selection, :default=true.
//    .brushOn(true)
//    // (optional) whether svg title element(tooltip) should be generated for each bar using
//    // the given function, :default=no
//    .title(function(d) { return "Value: " + d.value; })
//    // (optional) whether chart should render titles, :default = false
//    .renderTitle(true);
    
}
