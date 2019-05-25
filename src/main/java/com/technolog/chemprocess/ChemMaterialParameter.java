package com.technolog.chemprocess;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ChemMaterialParameter implements Serializable
{

	@Id
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private ChemMaterial material;

	@Id
	@ManyToOne
	@JoinColumn
	private ChemParameter param;

	@NotNull
	private final double value;

	protected ChemMaterialParameter()
	{
		material = null;
		param = null;
		value = 0;
	}

	public ChemMaterialParameter(ChemMaterial material, ChemParameter param, long value)
	{
		this.material = material;
		this.param = param;
		this.value = value;
	}

	public void setMaterial(ChemMaterial material)
	{
		this.material = material;
	}

	public ChemMaterial getMaterial()
	{
		return material;
	}

	public void setParam(ChemParameter param)
	{
		this.param = param;
	}

	public ChemParameter getParam()
	{
		return param;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(material.getName(), param.getName());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof ChemMaterialParameter))
			return false;

		ChemMaterialParameter other = (ChemMaterialParameter) obj;

		return Objects.equals(this.material.getName(), other.material.getName()) &&
				Objects.equals(this.param.getName(), other.param.getName()) &&
				Objects.equals(this.value, other.value);
	}

	public double getValue()
	{
		return value;
	}
}
