package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/process")
public class ChemProcessController
{

	@Autowired
	private ChemMaterialRepository materialRepo;

	@GetMapping
	public String processForm(@Valid @ModelAttribute("process") ChemProcess process, Errors errors, Model model)
	{

        List<ChemMaterial> materials = (List<ChemMaterial>) materialRepo.findAll();

        ChemProcessor.perform(process, materialRepo.findByName(process.getMaterialName()), 16.0d);

        model.addAttribute("process", process);
        model.addAttribute("materials", materials);

        try
		{
            Base64.Encoder encoder = Base64.getEncoder();
            String tempGraphEncoded = encoder.encodeToString(GraphGenerator.getTemperatureGraphImage(process));
            model.addAttribute("tempGraph", tempGraphEncoded);

            String consistGraphEncoded = encoder.encodeToString(GraphGenerator.getViscosityGraphImage(process));
            model.addAttribute("viscosityGraph", consistGraphEncoded);
        }
		catch (IOException e)
		{
            model.addAttribute("tempGraph", "");
            model.addAttribute("viscosityGraph", "");
            e.printStackTrace();
        }

        return "processForm";
    }

	@GetMapping(params = "action=get_report")
	public HttpEntity<byte[]> sendProcessReport(ChemProcess process)
	{
		ChemProcessor.perform(process, materialRepo.findByName(process.getMaterialName()), 16.0d);
		byte[] report = ReportGenerator.getXlsReport(process);

		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xls");
		header.setContentLength(report.length);

		return new HttpEntity<>(report, header);
	}
}
