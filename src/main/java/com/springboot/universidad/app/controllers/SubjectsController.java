package com.springboot.universidad.app.controllers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.universidad.app.models.dao.ISubjectsDao;
import com.springboot.universidad.app.models.entity.Subjects;

@Controller
public class SubjectsController {
	
	@Autowired
	private ISubjectsDao subjectDao;
	
	@Secured("ROLE_USER")
	@GetMapping({"/listar", "/"})
	public String listar(Model model) {
		
		model.addAttribute("title", "Listado de Materias: ");
		model.addAttribute("subjects", subjectDao.findAll());
		
		return "listar";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") int id, Model model) {
		
		Subjects subject = null;
		
		if(id>0) {
			subject = subjectDao.findOne(id);
		} else {
			return "redirect:/listar";
		}
		
		model.addAttribute("title", "Detalle materia");
		model.addAttribute("subjects", subject);
		
		
		return "ver";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") int id, Model model) {
		Subjects subject = null;
		
		if(id > 0) {
			subject = subjectDao.findOne(id);
			
			model.addAttribute("title", "Formulario de Materia");
			model.addAttribute("subject", subjectDao.findOne(id));
		}
		
		return "editar";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/editar")
	public String guardar(@Valid Subjects subject, RedirectAttributes flash) {
		subjectDao.save(subject);
		flash.addFlashAttribute("success", "Editado con éxito");		
		
		return "redirect:/ver/" + subject.getId();
	}
}
