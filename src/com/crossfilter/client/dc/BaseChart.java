package com.crossfilter.client.dc;

import com.crossfilter.client.Dimension;
import com.crossfilter.client.Group;
import com.crossfilter.client.Group.KeyValue;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * 
 * https://github.com/NickQiZhu/dc.js/wiki/API#wiki-base-chart
 * 
 */
public abstract class BaseChart<T, K> extends JavaScriptObject {
    private static int next = 1;
    
    public static String getNextId() {
        return "_dc_chart_" + next++;
    }
    
    protected BaseChart() {
    }

    public final native BaseChart<T, K> width(int width) /*-{
		return this.width(width);
    }-*/;

    public final native BaseChart<T, K> height(int height) /*-{
		return this.height(height);
    }-*/;

    public final native BaseChart<T, K> transitionDuration(int transitionDuration) /*-{
		return this.transitionDuration(transitionDuration);
    }-*/;

    public final native BaseChart<T, K> margins(int top, int right, int bottom, int left) /*-{
		return this.margins({
			top : top,
			right : right,
			bottom : bottom,
			left : left
		});
    }-*/;

    public final native BaseChart<T, K> dimension(Dimension<T, K> dimension) /*-{
		return this.dimension(dimension);
    }-*/;

    public final native BaseChart<T, K> group(Group<T, K> group) /*-{
		return this.group(group);
    }-*/;

    public final native void filterAll() /*-{
		this.filterAll();
    }-*/;

    public final native void render() /*-{
		this.render();
    }-*/;

    public final native void redraw() /*-{
		this.redraw();
    }-*/;

    
    public final native void root(Element root) /*-{
        this.root($wnd.d3.select(root));
        this._anchor = root.id;
    }-*/;
    
    public final native String anchorName() /*-{
        return this.anchorName();
    }-*/;
    
    public static abstract class Accessor {
        protected abstract JavaScriptObject getFunction();
    }

    public static abstract class IntAccessor extends Accessor {
        public abstract int getValue(KeyValue entry);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.dc.BaseChart.IntAccessor::getValue(Lcom/crossfilter/client/Group$KeyValue;)(v);
			};
        }-*/;
    }

    public static abstract class DoubleAccessor extends Accessor {
        public abstract double getValue(KeyValue entry);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.dc.BaseChart.DoubleAccessor::getValue(Lcom/crossfilter/client/Group$KeyValue;)(v);
			};
        }-*/;
    }

    public static abstract class StringAccessor extends Accessor {
        public abstract String getValue(KeyValue entry);

        @Override
        protected JavaScriptObject getFunction() {
            return f();
        }

        private final native JavaScriptObject f() /*-{
			var self = this;
			return function(v) {
				return self.@com.crossfilter.client.dc.BaseChart.StringAccessor::getValue(Lcom/crossfilter/client/Group$KeyValue;)(v);
			};
        }-*/;
    }
    
    public final void keyAccessor(Accessor accessor) {
        keyAccessor(accessor.getFunction());
    }

    private final native BaseChart<T, K> keyAccessor(JavaScriptObject func) /*-{
		return this.keyAccessor(func);
    }-*/;

    public final void valueAccessor(Accessor accessor) {
        valueAccessor(accessor.getFunction());
    }

    private final native BaseChart<T, K> valueAccessor(JavaScriptObject func) /*-{
		return this.valueAccessor(func);
    }-*/;

    public final void label(StringAccessor accessor) {
        label(accessor.getFunction());
    }

    private final native BaseChart<T, K> label(JavaScriptObject func) /*-{
		return this.label(func);
    }-*/;

    public final native BaseChart<T, K> renderLabel(boolean render) /*-{
		return this.renderLabel(render);
    }-*/;

    public final void title(StringAccessor accessor) {
        title(accessor.getFunction());
    }

    private final native BaseChart<T, K> title(JavaScriptObject func) /*-{
		return this.title(func);
    }-*/;

    public final native BaseChart<T, K> renderTitle(boolean render) /*-{
		return this.renderTitle(render);
    }-*/;

}
