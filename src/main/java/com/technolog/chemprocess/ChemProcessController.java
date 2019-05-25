package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/process")
public class ChemProcessController
{

	@Autowired
	private ChemMaterialRepository materialRepo;

	@GetMapping
	public String processForm(Model model)
	{
		List<ChemMaterial> materials = (List<ChemMaterial>) materialRepo.findAll();

		model.addAttribute("materials", materials);

		return "processForm";
	}

	@GetMapping("/result")
	public String resultForm(ChemProcess process, Model model)
	{
		List<ChemMaterial> materials = (List<ChemMaterial>) materialRepo.findAll();

		model.addAttribute("process", process);
		model.addAttribute("materials", materials);

		return "resultForm";
	}
}
