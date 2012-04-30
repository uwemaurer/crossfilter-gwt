package com.crossfilter.client;

import java.util.Date;

import com.crossfilter.client.Dimension.Grouping;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;

public class Util {

    public static Date day(Date date) {
        return new Date((date.getTime() / 86400000L) * 86400000L);
    }

    public static JsDate toJs(Date date) {
        return JsDate.create((double) date.getTime());
    }

    public static Date toGWT(JsDate jsDate) {
        return new Date((long) jsDate.getTime());
    }
    
    public static Grouping<Double> createGroupingMultipleOf(final double m) {
        return new Grouping<Double>() {
            
            @Override
            protected JavaScriptObject getGroupFunction() {
                return f(m);
            }

            private final native JavaScriptObject f(double m) /*-{
                return function(v) {
                    return Math.round(v / m) * m;
                };
            }-*/;
        };
    }

    public static Grouping<Date> createDayGrouping() {
        return new DayGrouping();
    }

    private static class DayGrouping extends Grouping<Date> {

        @Override
        protected JavaScriptObject getGroupFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			return function(v) {
				return $wnd.d3.time.day(v);
			};
        }-*/;
    }
}
