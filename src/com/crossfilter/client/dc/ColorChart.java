package com.crossfilter.client.dc;

import java.util.List;

import com.crossfilter.client.Group.KeyValue;
import com.google.gwt.core.client.JsArrayString;

/**
 * 
 * https://github.com/NickQiZhu/dc.js/wiki/API#wiki-color-chart
 * 
 */
public abstract class ColorChart<T, K> extends BaseChart<T, K> {

    protected ColorChart() {
    }

    // TODO maybe other return values need to be supported, eg date
    public interface ColorAccessor {
        int color(KeyValue entry, int index);
    }

    public final void colors(List<String> colors) {
        JsArrayString array = JsArrayString.createArray().cast();
        for (String c : colors) {
            array.push(c);
        }
        colors(array);
    }

    public final native ColorChart<T, K> colors(JsArrayString colors) /*-{
		return this.colors(colors);
    }-*/;

    public final native ColorChart<T, K> colorAccessor(ColorAccessor accessor) /*-{
		var func = function(d, i) {
			return accessor.@com.crossfilter.client.dc.ColorChart.ColorAccessor::color(Lcom/crossfilter/client/Group$KeyValue;I)(d,i);
		};
		return this.colorAccessor(func);
    }-*/;

}
