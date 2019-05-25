package com.technolog.chemprocess;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ChemParameter
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotEmpty
	private final String name;

	private final String units;

	@NotNull
	private Type type;

	@OneToMany(mappedBy = "param", cascade = CascadeType.ALL)
	@JsonIgnore
	private final Set<ChemMaterialParameter> materialParams;

	protected ChemParameter()
	{
		name = null;
		units = null;
		type = null;
		materialParams = new HashSet<>();
	}

	public ChemParameter(String name, String units, Type type)
	{
		this.name = name;
		this.units = units;
		this.type = type;
		this.materialParams = new HashSet<>();
	}

	public String getName()
	{
		return this.name;
	}

	public String getUnits()
	{
		return units;
	}

	public Type getType()
	{
		return type;
	}

	public static enum Type
	{
		PROPERTY, MULTIPLIER
	}
}
