package com.springboot.universidad.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.universidad.app.models.dao.IStudentDao;
import com.springboot.universidad.app.models.dao.ISubjectsDao;
import com.springboot.universidad.app.models.entity.Student;
import com.springboot.universidad.app.models.entity.Subjects;

@Controller
public class StudentController {
	
	@Autowired
	private ISubjectsDao subjectDao;
	
	@Autowired
	private IStudentDao studentDao;
	
	@GetMapping("/inscribirse/{id}")
	public String inscribirse(@PathVariable(value = "id") int id, RedirectAttributes flash, Principal principal) {
		Student alumno = null;
		
		if(principal != null) {
			alumno = studentDao.findByUsername(principal.getName());
			
			Subjects materia = null;
			if (id>0) {
				materia = subjectDao.findOne(id);
				if(!alumno.getMaterias().contains(materia) && materia.getCupAlumn() > 0) {
					materia.decCupAlumn();
					alumno.addMaterias(materia);
					studentDao.save(alumno);
					subjectDao.save(materia);
					flash.addFlashAttribute("success", "Te inscribiste a " + materia.getName() + " correctamente!");
				} else {
					String mensajeFlash = (alumno.getMaterias().contains(materia)) ? "Ya estas inscripto en la materia" : "Cupo lleno";
					flash.addFlashAttribute("error", mensajeFlash);
				}
			} else {
				flash.addFlashAttribute("Error", "El Id no puede ser 0");
			}
					
		} else {
			flash.addFlashAttribute("error", "No ha iniciado Sesión");
		}
		
		
		return "redirect:/ver/" + id;
	}

}
