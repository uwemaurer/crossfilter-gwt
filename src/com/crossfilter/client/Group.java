package com.crossfilter.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class Group<T, K> extends JavaScriptObject {

    protected Group() {
    }

    /**
     * Returns the number of distinct values in the group, independent of any
     * filters; the cardinality.
     * 
     */
    public final native int size() /*-{
		return this.size();
    }-*/;

    /**
     * A convenience method for setting the reduce functions to count records;
     * 
     * @return this group.
     */
    public final native Group<T, K> reduceCount() /*-{
		return this.reduceCount();
    }-*/;

    // public final native Group<T, K> reduceSum() /*-{
    // return this.reduceSum();
    // }-*/;

    // public final native Group<T, K> order() /*-{
    // return this.order();
    // }-*/;

    public final native Group<T, K> orderNatural() /*-{
		return this.orderNatural();
    }-*/;

    public static class KeyValue extends JavaScriptObject {
        protected KeyValue() {

        }

        public final native double getKey() /*-{
			return this.key;
        }-*/;

        public final native String getKeyAsString() /*-{
			return this.key;
        }-*/;

        public final native JavaScriptObject getKeyAsJSO() /*-{
            return this.key;
        }-*/;

        public final native double getValue() /*-{
			return this.value;
        }-*/;

    }

    public final native JsArray<KeyValue> top(int k) /*-{
		return this.top(k);
    }-*/;

    public final native JsArray<KeyValue> all() /*-{
		return this.all();
    }-*/;
}
