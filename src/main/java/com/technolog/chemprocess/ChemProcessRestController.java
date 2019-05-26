package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
	public ChemMaterial getFinalState(ChemProcess process)
	{
		ChemMaterial material = materialRepo.findByName(process.getMaterialName());
		return material;
	}

	@GetMapping("/graph")
	public ResponseEntity<byte[]> getProcessGraph(ChemProcess process)
	{
		byte[] graph = {0, 1};
		return new ResponseEntity<>(graph, HttpStatus.OK);
	}

	@GetMapping("/report")
	public HttpEntity<byte[]> getProcessReport(ChemProcess process)
	{

		process.setProductivity(0.0f);
		process.setDensity(0.0f);
		process.setTemperature(0.0f);
		byte[] report = ReportGenerator.getXlsReport(process);

		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xls");
		header.setContentLength(report.length);

		return new HttpEntity<>(report, header);
	}
}
