package com.crossfilter.client.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.crossfilter.client.BarChart;
import com.crossfilter.client.Crossfilter;
import com.crossfilter.client.Dimension;
import com.crossfilter.client.Dimension.DateDimensionType;
import com.crossfilter.client.Dimension.DoubleDimensionType;
import com.crossfilter.client.Dimension.DoubleGrouping;
import com.crossfilter.client.Dimension.IntDimensionType;
import com.crossfilter.client.Dimension.IntGrouping;
import com.crossfilter.client.Dimension.StringDimensionType;
import com.crossfilter.client.Group;
import com.crossfilter.client.Group.KeyValue;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * demo / test application
 * 
 * @author uwe
 * 
 */
public class CrossfilterDemo implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button sendButton = new Button("Send");
        RootPanel.get().add(sendButton);

        demo();
    }

    private static class Data {
        @Override
        public String toString() {
            return "Data [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3
                + ", value4=" + value4 + "]";
        }

        private int value1;
        private int value2;
        private double value3;
        private String value4;
        private Date value5;

        public Data(int v1, int v2, double v3, String v4, Date v5) {
            value1 = v1;
            value2 = v2;
            value3 = v3;
            value4 = v4;
            value5 = v5;
        }

        public int getValue1() {
            return value1;
        }

        public void setValue1(int value1) {
            this.value1 = value1;
        }

        public int getValue2() {
            return value2;
        }

        public void setValue2(int value2) {
            this.value2 = value2;
        }

        public double getValue3() {
            return value3;
        }

        public void setValue3(double value3) {
            this.value3 = value3;
        }

        public String getValue4() {
            return value4;
        }

        public void setValue4(String value4) {
            this.value4 = value4;
        }

        public Date getValue5() {
            return value5;
        }

        public void setValue5(Date value5) {
            this.value5 = value5;
        }

    }

    private void demo() {
        List<Data> data = new ArrayList<Data>();
        for (int i = 0; i < 2000; i++) {
            data.add(new Data(i, (int) (Math.random() * Math.random() * i), Math.random(), "abc"
                + (int) (Math.random() * 6), new Date(System.currentTimeMillis() + i * 1000000)));
        }

        Crossfilter<Data> crossfilter = Crossfilter.create(data);

        GWT.log("" + crossfilter.size());

        Dimension<Data, Integer> d1 = crossfilter.dimension(new IntDimensionType<CrossfilterDemo.Data>() {

            @Override
            public int getValue(Data object) {
                return object.getValue1();
            }
        });

        Dimension<Data, Integer> d2 = crossfilter.dimension(new IntDimensionType<CrossfilterDemo.Data>() {

            @Override
            public int getValue(Data object) {
                return object.getValue2();
            }
        });
        Dimension<Data, Double> d3 = crossfilter.dimension(new DoubleDimensionType<CrossfilterDemo.Data>() {

            @Override
            public double getValue(Data object) {
                return object.getValue3();
            }
        });
        Dimension<Data, String> d4 = crossfilter.dimension(new StringDimensionType<CrossfilterDemo.Data>() {

            @Override
            public String getValue(Data object) {
                return object.getValue4();
            }
        });
        Dimension<Data, Date> d5 = crossfilter.dimension(new DateDimensionType<CrossfilterDemo.Data>() {

            @Override
            public Date getValue(Data object) {
                return object.getValue5();
            }
        });
        List<Data> top = d2.top(5);
        for (Data da : top) {
            GWT.log("" + da);
        }

        Group<Data, Integer> g = d1.group(new IntGrouping() {

            @Override
            public int getGroup(int object) {
                return object;
            }
        });

        Group<Data, Integer> g2 = d2.group();
        Group<Data, Double> g3 = d3.group(new DoubleGrouping() {

            @Override
            public double getGroup(double object) {
                return Math.floor(object * 10) / 10;
            }

        });
        Group<Data, String> g4 = d4.group();
        Group<Data, Date> g5 = d5.group();

        JsArray<KeyValue> top2 = g3.top(5);
        for (int i = 0; i < top2.length(); i++) {
            GWT.log("" + top2.get(i).getValue());
        }
        BarChart chart = BarChart.create(d1, g, 1000).title("value1");
        BarChart chart2 = BarChart.create(d2, g2, 1000).title("value2");
        BarChart chart3 = BarChart.create(d3, g3, 1000).title("value3");
        BarChart chart5 = BarChart.createDate(d5, g5, 1000).title("value5");

        BarChart.render(Arrays.asList(chart, chart2, chart3, chart5));
    }

}
