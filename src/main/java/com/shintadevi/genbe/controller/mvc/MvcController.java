package com.shintadevi.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class MvcController {
	
	@GetMapping("dashboard")
	public String getIndex() {
		return "dashboard/index";
	}
	@GetMapping("input/person")
	public String getPerson() {
		return "dashboard/person";
	}
//	@GetMapping("input/pendidikan")
//	public String getPendidikan() {
//		return "dashboard/pendidikan";
//	}
	@GetMapping("input/pendidikan/{idPerson}")
	public String getPendidikan(Model model, @PathVariable Integer idPerson) {
		model.addAttribute("idPerson", idPerson);
		return "dashboard/pendidikan";
	}
	@GetMapping("data/pendidikan")
	public String getTabelPendidikan() {
		return "dashboard/tabelpendidikan";
	}
}
