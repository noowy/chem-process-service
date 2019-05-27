package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/process")
public class ChemProcessController
{

	@Autowired
	private ChemMaterialRepository materialRepo;

	@GetMapping
	public String processForm(ChemProcess process, Model model)
	{
		List<ChemMaterial> materials = (List<ChemMaterial>) materialRepo.findAll();

		ChemProcessor.perform(process, materialRepo.findByName(process.getMaterialName()));

		model.addAttribute("process", process);
		model.addAttribute("materials", materials);

		return "processForm";
	}

	@GetMapping(params = "action=get_report")
	public HttpEntity<byte[]> sendProcessReport(ChemProcess process)
	{
		ChemProcessor.perform(process, materialRepo.findByName(process.getMaterialName()));
		byte[] report = ReportGenerator.getXlsReport(process);

		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xls");
		header.setContentLength(report.length);

		return new HttpEntity<>(report, header);
	}
}
