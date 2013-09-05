package com.crossfilter.client.dc;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * creates a div with a title and reset link to reset the filter
 *
 */
public class ChartContainer extends Composite {

  
    private BaseChart<?, ?> chart;
    private InlineLabel titleWidget;
    private InlineLabel filter;
    private InlineLabel filter2;

    public ChartContainer(String title) {
        this(title, true);
    }

    public ChartContainer(String title, boolean withFilter) {
        initWidget(createContainer(title, withFilter));
    }
    
    public ChartContainer(BaseChart<?, ?> chart, String title, boolean withFilter) {
        initWidget(createContainer(title, withFilter));
        setChart(chart);
    }
    
    public static ChartContainer create(BaseChart<?, ?> chart, String title) {
            return new ChartContainer(chart, title , true);
    }
    
    public ChartContainer title(String title) {
        titleWidget.setText(title);
        return this;
    }
    
    public ChartContainer showFilter(boolean show) {
        filter2.setVisible(show);
        filter2.setStyleName("filter", show);
        filter.setStyleName("reset", show);
        filter.setVisible(show);
        return this;
    }
    
    @Override
    protected void onAttach() {
        super.onAttach();
        if (chart != null) {
            chart.root(getElement());
            getElement().setClassName("dc-chart");
            getElement().setId(chart.anchorName());
        }
    }

    private Widget createContainer(String title, boolean withFilter) {
        HTMLPanel p = new HTMLPanel("");
        titleWidget = new InlineLabel(title);
        p.add(titleWidget);

        
        filter = new InlineLabel(" Filter: ");
        p.add(filter);

        filter2 = new InlineLabel("");
        p.add(filter2);
    
        showFilter(withFilter);
        
        Anchor anchor = new Anchor(" Reset");
        anchor.setStyleName("reset");
        anchor.setVisible(false);
        anchor.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (chart != null) {
                    chart.filterAll();
                    Dc.redrawAll();
                }
            }
        });
        p.add(anchor);
    
        p.add(new SimplePanel());
        return p;
    }

    public <T, K> void setChart(BaseChart<T, K> chart) {
        this.chart = chart;
    }

    public String getSelector() {
        return "#" + getElement().getId();
    }
}
