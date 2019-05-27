package com.technolog.chemprocess;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;

public class GraphGenerator
{

	public static byte[] getConsistencyGraphImage(ChemProcess process)
	{
		Plot plot = new XYPlot();
		return new byte[]{2};
	}

	public static byte[] getTemperatureGraphImage(ChemProcess process)
	{
		JFreeChart chart = ChartFactory.createLineChart(
				"Temperature by applicat",
				"Temperature",
				"Applicat coord",
				ChemProcessor.getTemperatureSeries(process),
				PlotOrientation.VERTICAL,
				true,
				true,
				false
			);
		Plot plot = new XYPlot();
		return new byte[]{2};
	}
}
