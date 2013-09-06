package com.crossfilter.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.crossfilter.client.Crossfilter;
import com.crossfilter.client.Dimension;
import com.crossfilter.client.Dimension.DateReducer;
import com.crossfilter.client.Dimension.DoubleReducer;
import com.crossfilter.client.Dimension.IntGrouping;
import com.crossfilter.client.Dimension.IntReducer;
import com.crossfilter.client.Dimension.StringFilter;
import com.crossfilter.client.Dimension.StringReducer;
import com.crossfilter.client.Group;
import com.crossfilter.client.Group.KeyValue;
import com.crossfilter.client.SingleGroup;
import com.crossfilter.client.Util;
import com.crossfilter.client.dc.BarChart;
import com.crossfilter.client.dc.BaseChart;
import com.crossfilter.client.dc.BaseChart.ChartEventCallback;
import com.crossfilter.client.dc.ChartContainer;
import com.crossfilter.client.dc.Dc;
import com.crossfilter.client.dc.RowChart;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

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
        final List<Data> data = new ArrayList<Data>();
        for (int i = 0; i < 200; i++) {
            data.add(new Data(i, (int) (Math.random() * Math.random() * i), Math.random() * 10.0,
                "abc" + (int) (Math.random() * 6), new Date(System.currentTimeMillis() + i * 1000
                    * 86400)));
        }

        final Crossfilter<Data> crossfilter = Crossfilter.create(data);

        GWT.log("" + crossfilter.size());

        final Dimension<Data, Integer> d1 = crossfilter.dimension(new IntReducer<CrossfilterDemo.Data>() {

            @Override
            public int getValue(Data object) {
                return object.getValue1();
            }
        });

        Dimension<Data, Integer> d2 = crossfilter.dimension(new IntReducer<CrossfilterDemo.Data>() {

            @Override
            public int getValue(Data object) {
                return object.getValue2();
            }
        });
        Dimension<Data, Double> d3 = crossfilter.dimension(new DoubleReducer<CrossfilterDemo.Data>() {

            @Override
            public double getValue(Data object) {
                return object.getValue3();
            }
        });
        final Dimension<Data, String> d4 = crossfilter.dimension(new StringReducer<CrossfilterDemo.Data>() {

            @Override
            public String getValue(Data object) {
                return object.getValue4();
            }
        });
        final Dimension<Data, Date> d5 = crossfilter.dimension(new DateReducer<CrossfilterDemo.Data>() {

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

        final SingleGroup<Data, Integer> all = crossfilter.groupAll();
        Group<Data, Integer> g2 = d2.group();
        Group<Data, Double> g3 = d3.group(Util.createGroupingMultipleOf(0.4));
        Group<Data, String> g4 = d4.group();
        Group<Data, Date> g5 = d5.group(Util.createGroupingWeeks());

        JsArray<KeyValue> top2 = g3.top(5);
        for (int i = 0; i < top2.length(); i++) {
            // GWT.log("" + top2.get(i).getValue());
        }

        Group<Data, Date> g6 = d5.group(Util.createGroupingDays()).reduceSum(
            new IntReducer<CrossfilterDemo.Data>() {

                @Override
                public int getValue(Data object) {
                    return object.getValue1();
                }
            });

        {
            BarChart<Data, Integer> chart = BarChart.create();
            chart.dimension(d1).group(g);
            chart.x(Util.linearDomain(0, 1)).elasticX(true).elasticY(true);
            chart.width(600);
            RootPanel.get().add(ChartContainer.create(chart, "value1"));
        }

        {
            BarChart<Data, Integer> chart = BarChart.create();
            chart.dimension(d2).group(g2);
            chart.x(Util.linearDomain(0, 1)).elasticX(true).elasticY(true);
            chart.width(600);
            RootPanel.get().add(ChartContainer.create(chart, "value2"));
        }
        {
            BarChart<Data, Double> chart = BarChart.create();
            chart.dimension(d3).group(g3);
            chart.x(Util.linearDomain(0.0, 10.0)).elasticY(true);
            chart.width(600);
            RootPanel.get().add(ChartContainer.create(chart, "value3"));
        }
        {
            RowChart<Data, String> chart = RowChart.create();
            chart.dimension(d4).group(g4);
            chart.width(600);
            RootPanel.get().add(ChartContainer.create(chart, "value4"));
        }
        {
            BarChart<Data, Date> chart = BarChart.create();
            chart.dimension(d5).group(g5);
            chart.x(Util.timeDomain(0, 1)).elasticX(true).elasticY(true);
            chart.width(600);
            RootPanel.get().add(ChartContainer.create(chart, "value3"));
        }
      
      


        CellTable<Data> table = new CellTable<Data>();
        TextColumn<Data> nameColumn = new TextColumn<Data>() {
            @Override
            public String getValue(Data object) {
                return object.toString();
            }
        };
        table.addColumn(nameColumn);
        TextColumn<Data> nameColumn2 = new TextColumn<Data>() {
            @Override
            public String getValue(Data object) {
                return object.getValue5().toString();
            }
        };
        table.addColumn(nameColumn2);

        final ListDataProvider<Data> dataProvider = new ListDataProvider<Data>();
        dataProvider.addDataDisplay(table);
        final Label allLabel = new Label();
       
        
        {
            BarChart<Data, Date> chart = BarChart.create();
            chart.dimension(d5).group(g6);
            chart.x(Util.timeDomain(0, 1)).elasticX(true).elasticY(true);
            chart.width(600);
            chart.setOnPreRedraw(new ChartEventCallback<CrossfilterDemo.Data, Date>() {

                @Override
                public void onEvent(BaseChart<Data, Date> chart) {
                     allLabel.setText("" + all.getValueInt() + " of " + crossfilter.size());
                     dataProvider.getList().clear();
                     dataProvider.getList().addAll(d5.top(10));
                     dataProvider.refresh();
                   
                }
            });
            RootPanel.get().add(ChartContainer.create(chart, "value3"));
        }
        Button filter = new Button("filter");
        filter.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
               
                d4.filterFunction(new StringFilter() {

                    @Override
                    public boolean filter(String value) {
                        return value.equals("abc3");
                    }
                });
               
                Dc.redrawAll();
            }
        });
        RootPanel.get().add(filter);
        RootPanel.get().add(allLabel);
        RootPanel.get().add(table);
        Dc.renderAll();
    }

}
