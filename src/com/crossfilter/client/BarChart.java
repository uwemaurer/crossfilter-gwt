package com.crossfilter.client;

import java.util.Collection;

import com.crossfilter.client.Group.KeyValue;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class BarChart extends JavaScriptObject {

    protected BarChart() {
    }

    private static native BarChart create() /*-{
		var d3 = $wnd.d3;

		if (!$wnd.barChart_id) {
			$wnd.barChart_id = 0;
		}

		var margin = {
			top : 10,
			right : 10,
			bottom : 20,
			left : 10
		}, x, y = d3.scale.linear().range([ 150, 0 ]), id = $wnd.barChart_id++, axis = d3.svg
				.axis().orient("bottom"), brush = d3.svg.brush(), brushDirty, dimension, group, round, title;
		var axisy = d3.svg.axis().orient("left");

		function chart(div) {

			y.domain([ 0, group.top(1)[0].value ]);
			axisy.scale(y);
			var width = x.range()[1], height = y.range()[0];

			div.each(function() {
				var div = d3.select(this), g = div.select("g");

				// Create the skeletal chart.
				if (g.empty()) {
					div.select(".title").append("a").on("click", function() {
						reset();
					}).attr("class", "reset").text("reset").style("display",
							"none");

					g = div.append("svg").attr("width",
							width + margin.left + margin.right).attr("height",
							height + margin.top + margin.bottom).append("g")
							.attr(
									"transform",
									"translate(" + margin.left + ","
											+ margin.top + ")");

					g.append("clipPath").attr("id", "clip-" + id)
							.append("rect").attr("width", width).attr("height",
									height);

					g.selectAll(".bar").data([ "background", "foreground" ])
							.enter().append("path").attr("class", function(d) {
								return d + " bar";
							}).datum(group.all());

					g.selectAll(".foreground.bar").attr("clip-path",
							"url(#clip-" + id + ")");

					g.append("g").attr("class", "axis").attr("transform",
							"translate(0," + height + ")").call(axis);

					g.append("g").attr("class", "axis").attr("transform",
							"translate(20,0)").call(axisy);

					// Initialize the brush component with pretty resize handles.
					var gBrush = g.append("g").attr("class", "brush").call(
							brush);
					gBrush.selectAll("rect").attr("height", height);
					gBrush.selectAll(".resize").append("path").attr("d",
							resizePath);
				}

				// Only redraw the brush if set externally.
				if (brushDirty) {
					brushDirty = false;
					g.selectAll(".brush").call(brush);
					div.select(".title a").style("display",
							brush.empty() ? "none" : null);
					if (brush.empty()) {
						g.selectAll("#clip-" + id + " rect").attr("x", 0).attr(
								"width", width);
					} else {
						var extent = brush.extent();
						g.selectAll("#clip-" + id + " rect").attr("x",
								x(extent[0])).attr("width",
								x(extent[1]) - x(extent[0]));
					}
				}

				g.selectAll(".bar").attr("d", barPath);
			});

			function barPath(groups) {
				var path = [], i = -1, n = groups.length, d;
				while (++i < n) {
					d = groups[i];
					path.push("M", x(d.key), ",", height, "V", y(d.value),
							"h9V", height);
				}
				return path.join("");
			}

			function resizePath(d) {
				var e = 0 + (d == "e"), x = e ? 1 : -1, y = height / 3;
				return "M" + (.5 * x) + "," + y + "A6,6 0 0 " + e + " "
						+ (6.5 * x) + "," + (y + 6) + "V" + (2 * y - 6)
						+ "A6,6 0 0 " + e + " " + (.5 * x) + "," + (2 * y)
						+ "Z" + "M" + (2.5 * x) + "," + (y + 8) + "V"
						+ (2 * y - 8) + "M" + (4.5 * x) + "," + (y + 8) + "V"
						+ (2 * y - 8);
			}
		}

		brush.on("brushstart.chart", function() {
			var div = d3.select(this.parentNode.parentNode.parentNode);
			div.select(".title a").style("display", null);
		});

		brush.on("brush.chart", function() {
			var g = d3.select(this.parentNode), extent = brush.extent();
			if (round)
				g.select(".brush").call(
						brush.extent(extent = extent.map(round))).selectAll(
						".resize").style("display", null);
			g.select("#clip-" + id + " rect").attr("x", x(extent[0])).attr(
					"width", x(extent[1]) - x(extent[0]));
			dimension.filterRange(extent);
		});

		brush.on("brushend.chart", function() {
			if (brush.empty()) {
				var div = d3.select(this.parentNode.parentNode.parentNode);
				div.select(".title a").style("display", "none");
				div.select("#clip-" + id + " rect").attr("x", null).attr(
						"width", "100%");
				dimension.filterAll();
			}
		});

		chart.margin = function(_) {
			if (!arguments.length)
				return margin;
			margin = _;
			return chart;
		};

		chart.x = function(_) {
			if (!arguments.length)
				return x;
			x = _;
			axis.scale(x);
			brush.x(x);
			return chart;
		};

		chart.y = function(_) {
			if (!arguments.length)
				return y;
			y = _;
			return chart;
		};

		chart.dimension = function(_) {
			if (!arguments.length)
				return dimension;
			dimension = _;
			return chart;
		};

		chart.filter = function(_) {
			if (_) {
				brush.extent(_);
				dimension.filterRange(_);
			} else {
				brush.clear();
				dimension.filterAll();
			}
			brushDirty = true;
			return chart;
		};

		chart.group = function(_) {
			if (!arguments.length)
				return group;
			group = _;
			return chart;
		};

		chart.round = function(_) {
			if (!arguments.length)
				return round;
			round = _;
			return chart;
		};

		chart.title = function(_) {
			if (!arguments.length)
				return title;
			title = _;
			return chart;
		};

		var resultChart = d3.rebind(chart, brush, "on");

		var reset = function() {
			resultChart.filter(null);
			$wnd.renderAll();
		};
		return resultChart;
    }-*/;

    public interface RenderCallback {
        void renderAll();
    }
    
    public static final void render(Collection<BarChart> charts, RenderCallback callback) {
        @SuppressWarnings("unchecked")
        JsArray<JavaScriptObject> array = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
        for (BarChart chart : charts) {
            array.push(chart);
        }
        render(array, callback);
    }

    private static final native void render(JsArray<JavaScriptObject> charts, RenderCallback callback) /*-{
		$wnd.charts = charts;
		var d3 = $wnd.d3;
		var chart = d3.select("body").selectAll(".chart").data(charts);
		chart.enter().append("div").html(function(d) {
			return "<div class='title'>" + d.title() + "</div>";
		});
		chart.exit().remove();

		chart.each(function(chart) {
			chart.on("brush", renderAll).on("brushend", renderAll);
		});

		// Render the initial lists.
		//var list = d3.selectAll(".list").data([ flightList ]);

		// Render the total.
		//d3.selectAll("#total").text(formatNumber(flight.size()));

		renderAll();

		// Renders the specified chart or list.
		function render(method) {
			d3.select(this).call(method);
		}

		// Whenever the brush moves, re-rendering everything.
		function renderAll() {
			chart.each(render);
			//list.each(render);
			//d3.select("#active").text(formatNumber(all.value()));
			callback.@com.crossfilter.client.BarChart.RenderCallback::renderAll()();
		}
		$wnd.renderAll = renderAll;
    }-*/;

    public final native <T, K> BarChart set(Dimension<T, K> dim, Group<T, K> group, double from,
        double to, int width) /*-{
		var d3 = $wnd.d3;
		this.dimension(dim).group(group).x(
				d3.scale.linear().domain([ from, to ])
						.rangeRound([ 25, width ]));
		return this;
    }-*/;

    public final native <T, K> BarChart setDate(Dimension<T, K> dim, Group<T, K> group,
        JavaScriptObject from, JavaScriptObject to, int width) /*-{
		var d3 = $wnd.d3;
		this.dimension(dim).group(group).round(d3.time.day.round).x(
				d3.time.scale().domain([ from, to ]).rangeRound([ 25, width ]));

		return this;
    }-*/;

    public static <T, K> BarChart create(Dimension<T, K> dim, Group<T, K> group, double from,
        double to, int width) {
        BarChart barChart = create();
        barChart.set(dim, group, from, to, width);

        return barChart;
    }

    public static <T, K> BarChart create(Dimension<T, K> dim, Group<T, K> group, int width) {
        JsArray<KeyValue> all = group.all();
        double min = all.get(0).getKey();
        double max = all.get(all.length() - 1).getKey();
        return create(dim, group, min, max, width);
    }

    public static <T, K> BarChart createDate(Dimension<T, K> dim, Group<T, K> group, int width) {
        JsArray<KeyValue> all = group.all();
        BarChart barChart = create();
        barChart.setDate(dim, group, all.get(0).getKeyAsJSO(),
            all.get(all.length() - 1).getKeyAsJSO(), width);

        return barChart;
    }

    public final native BarChart title(String title) /*-{
		return this.title(title);
    }-*/;
}
