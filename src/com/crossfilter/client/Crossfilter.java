package com.crossfilter.client;

import java.util.Collection;

import com.crossfilter.client.Dimension.Reducer;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class Crossfilter<T> extends JavaScriptObject {

    protected Crossfilter() {
    }

    public static native <T> Crossfilter<T> create() /*-{
		return $wnd.crossfilter();
    }-*/;

    public static <T> Crossfilter<T> create(Collection<T> data) {
        Crossfilter<T> crossfilter = create();
        return crossfilter.add(data);
    }

    public final native int size() /*-{
		return this.size();
    }-*/;

    public final native Crossfilter<T> add(T data) /*-{
		return this.add([ data ]);
    }-*/;

    public final Crossfilter<T> add(Collection<T> data) {
        JsArray<JavaScriptObject> array = JsArray.createArray().cast();
        for (T element : data) {
            push(array, element);
        }
        return this.add(array);
    };

    private final native Crossfilter<T> add(JsArray<JavaScriptObject> data) /*-{
		return this.add(data);
    }-*/;

    private final native void push(JsArray<JavaScriptObject> array, T value) /*-{
		array[array.length] = value;
    }-*/;

    public final <K> Dimension<T, K> dimension(final Reducer<T, K> type) {
        return dimension(type.getFunction());
    }

    private final native <K> Dimension<T, K> dimension(JavaScriptObject func) /*-{
		return this.dimension(func);
    }-*/;

    public final native <K> SingleGroup<T, K> groupAll() /*-{
		return this.groupAll();
    }-*/;
}
