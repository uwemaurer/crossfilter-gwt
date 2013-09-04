package com.crossfilter.client;

import com.crossfilter.client.Dimension.ObjectReducer;
import com.crossfilter.client.Dimension.Reducer;
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

     public final native <V> Group<T, K> reduceSum(final Reducer<T, V> type) /*-{
         return this.reduceSum(type.@com.crossfilter.client.Dimension.Reducer::getFunction()());
     }-*/;
     
     
    public final native <V> Group<T, K> reduce(ObjectReducer<T, V> reducer) /*-{
		return this
				.reduce(
						reducer.@com.crossfilter.client.Dimension.ObjectReducer::reduceAdd()(),
						reducer.@com.crossfilter.client.Dimension.ObjectReducer::reduceRemove()(),
						reducer.@com.crossfilter.client.Dimension.ObjectReducer::reduceInitialJs()());
    }-*/;
     
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

        public final native double getValueAsDouble() /*-{
			return this.value;
        }-*/;
        
        /**
         * cast to own type
         * @return
         */
        public final native <T> T getValue() /*-{
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
