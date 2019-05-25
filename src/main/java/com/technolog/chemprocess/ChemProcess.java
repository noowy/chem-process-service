package com.technolog.chemprocess;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ChemProcess
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String materialName;

	@NotNull
	private Float channelWidth;

	@NotNull
	private Float channelHeight;

	@NotNull
	private Float channelLength;

	@NotNull
	private Float hoodSpeed;

	@NotNull
	private Float hoodTemp;

	private Float productivity;

	private Float temperature;

	private Float density;

	public ChemProcess()
	{
		materialName = null;
		channelWidth = 0.0f;
		channelHeight = 0.0f;
		channelLength = 0.0f;
		hoodSpeed = 0.0f;
		hoodTemp = 0.0f;
	}

	public ChemProcess(String materialName,
					   Float channelWidth,
					   Float channelHeight,
					   Float channelLength,
					   Float hoodSpeed,
					   Float hoodTemp)
	{
		this.materialName = materialName;
		this.channelWidth = channelWidth;
		this.channelHeight = channelHeight;
		this.channelLength = channelLength;
		this.hoodSpeed = hoodSpeed;
		this.hoodTemp = hoodTemp;
	}

	public String getMaterialName()
	{
		return materialName;
	}

	public void setMaterialName(String materialName)
	{
		this.materialName = materialName;
	}

	public Float getChannelWidth()
	{
		return channelWidth;
	}

	public void setChannelWidth(Float channelWidth)
	{
		this.channelWidth = channelWidth;
	}

	public Float getChannelHeight()
	{
		return channelHeight;
	}

	public void setChannelHeight(Float channelHeight)
	{
		this.channelHeight = channelHeight;
	}

	public Float getChannelLength()
	{
		return channelLength;
	}

	public void setChannelLength(Float channelLength)
	{
		this.channelLength = channelLength;
	}

	public Float getProductivity()
	{
		return productivity;
	}

	public void setProductivity(Float productivity)
	{
		this.productivity = productivity;
	}

	public Float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(Float temperature)
	{
		this.temperature = temperature;
	}

	public Float getDensity()
	{
		return density;
	}

	public void setDensity(Float density)
	{
		this.density = density;
	}

	public Float getHoodSpeed()
	{
		return hoodSpeed;
	}

	public void setHoodSpeed(Float hoodSpeed)
	{
		this.hoodSpeed = hoodSpeed;
	}

	public Float getHoodTemp()
	{
		return hoodTemp;
	}

	public void setHoodTemp(Float hoodTemp)
	{
		this.hoodTemp = hoodTemp;
	}
}
