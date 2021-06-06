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

    private final static String ERROR = "error";
    private final static String SUCCESS = "success";

    @Autowired
    private ISubjectsDao subjectDao;

    @Autowired
    private IStudentDao studentDao;

    @GetMapping("/inscribirse/{id}")
    public String inscribirse(@PathVariable(value = "id") int id, RedirectAttributes flash, Principal principal) {
        String result = "redirect:/ver/" + id;

        Student alumno;

        if (principal == null) {
            flash.addFlashAttribute(ERROR, "No ha iniciado sesión");

            return result;
        }

        alumno = studentDao.findByUsername(principal.getName());

        Subjects materia;
        if (id <= 0) {
            flash.addFlashAttribute(ERROR, "Id incorrecto");

            return result;
        }

        materia = subjectDao.findOne(id);

        if (materia == null) {
            flash.addFlashAttribute(ERROR, "La materia no existe");

            return result;
        }

        if (alumno.getMaterias().contains(materia)) {
            flash.addFlashAttribute(ERROR, String.format("Ya estas inscripto en la materia %s", materia.getName()));

            return result;
        }

        if (materia.getCupAlumn() == 0) {
            flash.addFlashAttribute(ERROR, "Cupo lleno");

            return result;
        }


        materia.decCupAlumn();
        alumno.addMaterias(materia);
        studentDao.save(alumno);

        //Guardo tambien la materia para que impacte el cupAlumn--
        subjectDao.save(materia);
        flash.addFlashAttribute(SUCCESS, String.format("Te inscribiste a %s correctamente!", materia.getName()));


        return result;
    }

}
