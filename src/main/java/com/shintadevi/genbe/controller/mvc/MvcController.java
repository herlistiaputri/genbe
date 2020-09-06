package com.shintadevi.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MvcController {
	
	@GetMapping("dashboard")
	public String getIndex() {
		return "dashboard/index";
	}
	@GetMapping("person")
	public String getPerson() {
		return "dashboard/person";
	}
	@GetMapping("pendidikan")
	public String getPendidikan() {
		return "dashboard/pendidikan";
	}

}