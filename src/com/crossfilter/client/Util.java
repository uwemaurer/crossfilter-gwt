package com.crossfilter.client;

import java.util.Date;

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

}
