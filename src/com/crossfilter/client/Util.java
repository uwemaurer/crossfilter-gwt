package com.crossfilter.client;

import java.util.Date;

import com.crossfilter.client.Dimension.Grouping;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;

public class Util {

    public static JsDate toJs(Date date) {
        return JsDate.create((double) date.getTime());
    }

    public static Date toGWT(JsDate jsDate) {
        return new Date((long) jsDate.getTime());
    }

    public static Grouping<Double> createGroupingMultipleOf(double m) {
        return new GroupingFunction<Double>(roundToMultiple(m));
    }

    public static Grouping<Date> createGroupingDays() {
        return new GroupingFunction<Date>(roundToDays());
    }

    public static Grouping<Date> createGroupingWeeks() {
        return new GroupingFunction<Date>(roundToWeeks());
    }

    private static final native JavaScriptObject roundToMultiple(double m) /*-{
		return function(v) {
			return Math.round(v / m) * m;
		};
    }-*/;

    private static final native JavaScriptObject roundToDays() /*-{
		return function(v) {
			return $wnd.d3.time.day(v);
		};
    }-*/;

    private static final native JavaScriptObject roundToWeeks() /*-{
		return function(v) {
			return $wnd.d3.time.week(v);
		};
    }-*/;

    private static class GroupingFunction<K> extends Grouping<K> {
        private final JavaScriptObject function;

        public GroupingFunction(JavaScriptObject function) {
            this.function = function;
        }

        @Override
        protected JavaScriptObject getGroupFunction() {
            return function;
        }

    }
}
