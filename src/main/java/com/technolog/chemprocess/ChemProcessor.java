package com.technolog.chemprocess;

import java.lang.Math;

public class ChemProcessor
{
	public static void perform(ChemProcess process, ChemMaterial material)
	{

	}

	private double getProductivity(double W, double H, double Vu)
	{
		return (W * H * Vu) / 2 * getSq(W, H);
	}

	private double getSq(double W, double H)
	{
		return 0.125 * Math.pow(H / W, 2) - 0.625 * (H / W) + 1;
	}

	private double getStrainSpeed(double Vu, double H)
	{
		return Vu / H;
	}

	private double Mu(double Mu0, double b, double Tr, double R)
	{
		return Mu0 * Math.exp(-b * (getTemp(Tr, R, b) - Tr));
	}

	private double getTemp(double Tr, double R, double b)
	{
		return Tr + 1 / b * Math.log(R);
	}

	private double R(double b, double W, double H,
					 double Vu, double Mu0, double n, double z, double T0,
					 double p, double c, double alphaU, double Tu, double Tr)
	{
		return (b * qy(W, H, Vu, Mu0, n) + W * alphaU) / (b * qa(W, alphaU, Tu, Tr, b)) *
				(1 - Math.exp(-((z * b * qa(W, alphaU, Tu, Tr, b)) / (p * c * getProductivity(W, H, Vu))))) +
				Math.exp(b * (T0 - Tr - ((z * qa(W, alphaU, Tu, Tr, b)) / (p * c * getProductivity(W, H, Vu)))));
	}

	private double qy(double W, double H, double Vu, double Mu0, double n)
	{
		return W * H * Mu0 * Math.pow(getStrainSpeed(Vu, H), n + 1);
	}

	private double qa(double W, double alphaU, double Tu, double Tr, double b)
	{
		return W * alphaU * (1 / b - Tu + Tr);
	}
}
