package com.crossfilter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsDate;

public class Dimension<T, K> extends JavaScriptObject {

    protected Dimension() {
    }

    public static abstract class Reducer<T, K> {
        protected abstract JavaScriptObject getFunction();
    }

    public static abstract class IntReducer<T> extends Reducer<T, Integer> {
        public abstract int getValue(T object);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.IntReducer::getValue(Ljava/lang/Object;)(v);
			};
        }-*/;
    }

    public static abstract class DoubleReducer<T> extends Reducer<T, Double> {
        public abstract double getValue(T object);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.DoubleReducer::getValue(Ljava/lang/Object;)(v);
			};
        }-*/;
    }

    public static abstract class StringReducer<T> extends Reducer<T, String> {
        public abstract String getValue(T object);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.StringReducer::getValue(Ljava/lang/Object;)(v);
			};
        }-*/;
    }

    public static abstract class DateReducer<T> extends Reducer<T, Date> {
        public abstract Date getValue(T object);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private JsDate getTime(T object) {
            return Util.toJs(getValue(object));
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.DateReducer::getTime(Ljava/lang/Object;)(v);
			};
        }-*/;
    }

    public static abstract class ObjectReducer<T, V> {
        public abstract V reduceAdd(V result, T object);

        public abstract V reduceRemove(V result, T object);

        public abstract V reduceInitial();

        protected final native JavaScriptObject reduceAdd() /*-{
			var self = this;
			return function(a, b) {
				return self.@com.crossfilter.client.Dimension.ObjectReducer::reduceAdd(Ljava/lang/Object;Ljava/lang/Object;)(a,b);
			};
        }-*/;

        protected final native JavaScriptObject reduceRemove() /*-{
			var self = this;
			return function(a, b) {
				return self.@com.crossfilter.client.Dimension.ObjectReducer::reduceRemove(Ljava/lang/Object;Ljava/lang/Object;)(a,b);
			};
        }-*/;

        protected final native JavaScriptObject reduceInitialJs() /*-{
			var self = this;
			return function() {
				return self.@com.crossfilter.client.Dimension.ObjectReducer::reduceInitial()();
			};
        }-*/;
    }

    public static abstract class Grouping<K> {
        protected abstract JavaScriptObject getGroupFunction();
    }

    public static abstract class IntGrouping extends Grouping<Integer> {
        public abstract int getGroup(int value);

        @Override
        protected JavaScriptObject getGroupFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.IntGrouping::getGroup(I)(v);
			};
        }-*/;
    }

    public static abstract class DoubleGrouping extends Grouping<Double> {
        public abstract double getGroup(double value);

        @Override
        protected JavaScriptObject getGroupFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.DoubleGrouping::getGroup(D)(v);
			};
        }-*/;
    }

    public static abstract class StringGrouping extends Grouping<String> {
        public abstract String getGroup(String value);

        @Override
        protected JavaScriptObject getGroupFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.StringGrouping::getGroup(Ljava/lang/String;)(v);
			};
        }-*/;
    }

    public static abstract class DateGrouping extends Grouping<Date> {
        public abstract Date getGroup(Date value);

        private JsDate getGroup(JsDate value) {
            return Util.toJs(getGroup(Util.toGWT(value)));
        }

        @Override
        protected JavaScriptObject getGroupFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.Dimension.DateGrouping::getGroup(Lcom/google/gwt/core/client/JsDate;)(v);
			};
        }-*/;
    }

    public final native Group<T, K> group() /*-{
		return this.group();
    }-*/;

    public final Group<T, K> group(final Grouping<K> grouping) {
        return group(grouping.getGroupFunction());
    }

    private final native Group<T, K> group(JavaScriptObject func) /*-{
		return this.group(func);
    }-*/;

    public final native Group<T, K> filterAll() /*-{
		return this.filterAll();
    }-*/;

    /**
     * Filters records such that this dimension's value equals value, and
     * returns this dimension
     * 
     * @param value
     * @return
     */
    private final Group<T, K> filterExact(K value) {
        if (value.getClass() == Integer.class) {
            return filterExact((Integer) value);
        } else if (value.getClass() == Double.class) {
            return filterExact((Double) value);
        } else if (value.getClass() == String.class) {
            return filterExact((String) value);
        }
        return null;
    }

    private final native Group<T, K> filterExact(int value) /*-{
		return this.filterExact(value);
    }-*/;

    private final native Group<T, K> filterExact(double value) /*-{
		return this.filterExact(value);
    }-*/;

    private final native Group<T, K> filterExact(String value) /*-{
		return this.filterExact(value);
    }-*/;

    /**
     * Filters records such that this dimension's value is greater than or equal
     * to "from", and less than "to", returning this dimension.
     * 
     * @param from
     * @param to
     * @return
     */
    public final Group<T, K> filterRange(K from, K to) {
        if (from.getClass() == Integer.class) {
            return filterRange((Integer) from, (Integer) to);
        } else if (from.getClass() == Double.class) {
            return filterRange((Double) from, (Double) to);
        } else if (from.getClass() == String.class) {
            return filterRange((String) from, (String) to);
        }
        return null;
    }

    private final native Group<T, K> filterRange(int from, int to) /*-{
		return this.filterRange([ from, to ]);
    }-*/;

    private final native Group<T, K> filterRange(double from, double to) /*-{
		return this.filterRange([ from, to ]);
    }-*/;

    private final native Group<T, K> filterRange(String from, String to) /*-{
		return this.filterRange([ from, to ]);
    }-*/;

    private final native JsArray<JavaScriptObject> topJs(int n) /*-{
		return this.top(n);
    }-*/;

    private final native JsArray<JavaScriptObject> bottomJs(int n) /*-{
		return this.bottom(n);
    }-*/;

    private final native T get(JsArray<JavaScriptObject> obj, int index) /*-{
		return obj[index];
    }-*/;

    public final List<T> top(int n) {
        JsArray<JavaScriptObject> topList = topJs(n);
        return toList(topList);
    }

    public final List<T> bottom(int n) {
        JsArray<JavaScriptObject> topList = bottomJs(n);
        return toList(topList);
    }

    private List<T> toList(JsArray<JavaScriptObject> topList) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < topList.length(); i++) {
            list.add(get(topList, i));
        }
        return list;
    }

    public final native SingleGroup<T, K> groupAll() /*-{
		return this.groupAll();
    }-*/;

    public static JavaScriptObject _getFunction(Reducer<?, ?> func) {
        return func.getFunction();
    }
}
