package com.technolog.chemprocess;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.ImageEncoderFactory;
import org.jfree.chart.encoders.SunJPEGEncoderAdapter;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GraphGenerator
{
	private static int WIDTH = 500;
	private static int HEIGHT = 300;

	public static byte[] getViscosityGraphImage(ChemProcess process) throws IOException
	{
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Viscosity by channel length",
				"Channel Length",
				"Viscosity",
				seriesMapper(process.getViscosityByLengthSeries(), "Viscosity"),
				PlotOrientation.VERTICAL,
				false,
				true,
				false
		);

		BufferedImage plotImage = chart.createBufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB, null);
		SunJPEGEncoderAdapter encoder = new SunJPEGEncoderAdapter();

		return encoder.encode(plotImage);
	}

	public static byte[] getTemperatureGraphImage(ChemProcess process) throws IOException
	{
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Temperature by channel length",
				"Channel Length",
				"Temperature",
				seriesMapper(process.getTempByLengthSeries(), "Temperature"),
				PlotOrientation.VERTICAL,
				false,
				true,
				false
			);

		BufferedImage plotImage = chart.createBufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB, null);
		SunJPEGEncoderAdapter encoder = new SunJPEGEncoderAdapter();

		return encoder.encode(plotImage);
	}

	public static XYDataset seriesMapper(ArrayList<HashMap<String, Double>> series, String seriesName)
	{
		XYSeriesCollection result = new XYSeriesCollection();
		XYSeries chartSeries = new XYSeries(seriesName);
		for (HashMap<String, Double> item : series)
		{
			chartSeries.add(
					item.getOrDefault("X", 0.0d),
					item.getOrDefault("Y", 0.0d)
			);
		}
		result.addSeries(chartSeries);
		return result;
	}
}
