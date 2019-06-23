package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path="/api/process")
public class ChemProcessRestController
{

	@Autowired
	private ChemMaterialRepository materialRepo;

	@GetMapping("/result")
	public ChemProcess getFinalState(@Valid ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		ChemProcessor.perform(process, material, 16.0d);
		return process;
	}

	@GetMapping(value = "/graph/temp", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getProcessTempGraph(@Valid ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		ChemProcessor.perform(process, material, 16.0d);

		byte[] graph;
		try
		{
			graph = GraphGenerator.getTemperatureGraphImage(process);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(graph, HttpStatus.OK);
	}

	@GetMapping(value = "/graph/viscosity", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getProcessViscosityGraph(@Valid ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		ChemProcessor.perform(process, material, 16.0d);

		byte[] graph;
		try
		{
			graph = GraphGenerator.getViscosityGraphImage(process);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(graph, HttpStatus.OK);
	}

	@GetMapping("/report")
	public HttpEntity<byte[]> getProcessReport(@Valid ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		ChemProcessor.perform(process, material, 16.0d);
		byte[] report = ReportGenerator.getXlsReport(process);

		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xls");
		header.setContentLength(report.length);

		return new HttpEntity<>(report, header);
	}
}
