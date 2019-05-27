package com.technolog.chemprocess;

import org.jfree.data.category.DefaultCategoryDataset;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ChemProcessor
{
	public static void perform(ChemProcess process, ChemMaterial material)
	{
		if (material == null)
		{
			process.setProductivity(0.0f);
			process.setTemperature(0.0f);
			process.setConsistency(0.0f);
			return;
		}
		Set<ChemMaterialParameter> materialParams = material.getMaterialParams();
		HashMap<String, Double> params = new HashMap<>();
		for (ChemMaterialParameter materialParam : materialParams)
		{
			params.put(materialParam.getParam().getName(), (double) materialParam.getValue());
		}
		double R = R(params.get("Temperature Viscosity Ratio"),
					 process.getChannelWidth(),
					 process.getChannelHeight(),
				     process.getHoodSpeed(),
					 params.get("Consistency Ratio"),
					 params.get("Flow Index"),
					 process.getChannelLength(),
					 params.get("Melting Temperature"),
				     params.get("Density"),
				     params.get("Specific Heat"),
					 params.get("Heat Transfer Coefficient"),
					 process.getHoodTemp(),
					 params.get("Reduction Temperature"));

		process.setProductivity((float) getProductivity(process.getChannelWidth(),
														process.getChannelHeight(),
														process.getHoodTemp()));
		process.setConsistency((float) getConsistency(params.get("Consistency Ratio"),
													  params.get("Temperature Viscosity Ratio"),
													  params.get("Reduction Temperature"),
													  R));
		process.setTemperature((float) getTemp(params.get("Reduction Temperature"),
											   R,
											   params.get("Temperature Viscosity Ratio")));
	}

	public static DefaultCategoryDataset getTemperatureSeries(ChemProcess process)
	{
		return new DefaultCategoryDataset();
	}

	private static double getProductivity(double W, double H, double Vu)
	{
		return (W * H * Vu) / 2 * getSq(W, H);
	}

	private static double getSq(double W, double H)
	{
		return 0.125 * Math.pow(H / W, 2) - 0.625 * (H / W) + 1;
	}

	private static double getStrainSpeed(double Vu, double H)
	{
		return Vu / H;
	}

	private static double getConsistency(double Mu0, double b, double Tr, double R)
	{
		return Mu0 * Math.exp(-b * (getTemp(Tr, R, b) - Tr));
	}

	private static double getTemp(double Tr, double R, double b)
	{
		return Tr + 1 / b * Math.log(R);
	}

	private static double R(double b, double W, double H,
					 double Vu, double Mu0, double n, double z, double T0,
					 double p, double c, double alphaU, double Tu, double Tr)
	{
		return (b * qy(W, H, Vu, Mu0, n) + W * alphaU) / (b * qa(W, alphaU, Tu, Tr, b)) *
				(1 - Math.exp(-((z * b * qa(W, alphaU, Tu, Tr, b)) / (p * c * getProductivity(W, H, Vu))))) +
				Math.exp(b * (T0 - Tr - ((z * qa(W, alphaU, Tu, Tr, b)) / (p * c * getProductivity(W, H, Vu)))));
	}

	private static double qy(double W, double H, double Vu, double Mu0, double n)
	{
		return W * H * Mu0 * Math.pow(getStrainSpeed(Vu, H), n + 1);
	}

	private static double qa(double W, double alphaU, double Tu, double Tr, double b)
	{
		return W * alphaU * (1 / b - Tu + Tr);
	}
}
