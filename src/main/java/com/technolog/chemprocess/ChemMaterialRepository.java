package com.technolog.chemprocess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemMaterialRepository extends CrudRepository<ChemMaterial, Long>
{

	ChemMaterial findByName(String name);
}
