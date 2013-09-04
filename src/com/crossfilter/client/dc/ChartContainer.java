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

    private static int next = 1;
    private BaseChart<?, ?> chart;

    public ChartContainer(String title) {
        this(title, true);
    }

    public ChartContainer(String title, boolean withFilter) {
        initWidget(createContainer(title, withFilter));
    }

    private Widget createContainer(String title, boolean withFilter) {
        HTMLPanel p = new HTMLPanel("");
        p.add(new InlineLabel(title));

        if (withFilter) {
            InlineLabel filter = new InlineLabel("Filter: ");
            filter.setStyleName("reset");
            filter.setVisible(false);
            p.add(filter);
    
            InlineLabel filter2 = new InlineLabel("");
            filter2.setStyleName("filter");
            p.add(filter2);
        }
        
        Anchor anchor = new Anchor("reset");
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
        p.getElement().setId("_dc_chart_" + next++);
        return p;
    }

    public <T, K> void setChart(BaseChart<T, K> chart) {
        this.chart = chart;
    }

    public String getSelector() {
        return "#" + getElement().getId();
    }
}
