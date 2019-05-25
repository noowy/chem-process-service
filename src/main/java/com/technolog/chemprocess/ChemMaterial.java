package com.technolog.chemprocess;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class ChemMaterial
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotEmpty
	private final String name;

	private final String description;

	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
	private final Set<ChemMaterialParameter> materialParams;

	protected ChemMaterial()
	{
		name = null;
		description = null;
		materialParams = null;
	}

	public ChemMaterial(String name, String description, ChemMaterialParameter... materialParams)
	{
		this.name = name;
		this.description = description;
		for (ChemMaterialParameter materialParam : materialParams)
			materialParam.setMaterial(this);
		this.materialParams = Stream.of(materialParams).collect(Collectors.toSet());
	}

	public Long getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public String getDescription()
	{
		return description;
	}

	public Set<ChemMaterialParameter> getMaterialParams()
	{
		return materialParams;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getName());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof ChemMaterial))
			return false;

		ChemMaterial other = (ChemMaterial) obj;

		return Objects.equals(this.getName(), other.getName());
	}
}
