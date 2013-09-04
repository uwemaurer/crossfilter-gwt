package com.crossfilter.client.dc;


public class Dc {
    public static native void renderAll() /*-{
		$wnd.dc.renderAll();
    }-*/;

    public static native void renderAll(String group) /*-{
		$wnd.dc.renderAll(group);
    }-*/;

    public static native void redrawAll() /*-{
		$wnd.dc.redrawAll();
    }-*/;

    public static native void redrawAll(String group) /*-{
		$wnd.dc.redrawAll(group);
    }-*/;

}
