package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/process")
public class ChemProcessRestController
{

	@Autowired
	private ChemMaterialRepository materialRepo;

	@GetMapping("/result")
	public ChemMaterial getFinalState(@RequestBody ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		return material;
	}

	@GetMapping("/graph")
	public ResponseEntity<byte[]> getProcessGraph(@RequestBody ChemProcess process)
	{
		byte[] graph = {0, 1};
		return new ResponseEntity<>(graph, HttpStatus.OK);
	}
}
