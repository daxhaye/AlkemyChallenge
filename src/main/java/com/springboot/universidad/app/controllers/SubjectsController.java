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

    private final static String TITLE = "title";
    private final static String SUBJECTS = "subjects";

    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    @Autowired
    private ISubjectsDao subjectDao;

    @Secured("ROLE_USER")
    @GetMapping({"/listar", "/"})
    public String listar(Model model) {

        model.addAttribute(TITLE, "Listado de Materias: ");
        model.addAttribute(SUBJECTS, subjectDao.findAll());

        return "listar";
    }

    @Secured("ROLE_USER")
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") int id, Model model) {

        Subjects subject;

        if (id <= 0) {
            return "redirect:/listar";
        }

        subject = subjectDao.findOne(id);

        model.addAttribute(TITLE, "Detalle materia");
        model.addAttribute(SUBJECTS, subject);

        return "ver";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") int id, Model model) {
        Subjects subject;

        if (id <= 0) {
            return "redirect:/listar";
        }

        subject = subjectDao.findOne(id);
        if (subject == null) {
            return "redirect:/listar";
        }

        model.addAttribute(TITLE, "Formulario de Materia");
        model.addAttribute(SUBJECTS, subjectDao.findOne(id));

        return "editar";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/editar")
    public String guardar(@Valid Subjects subject, RedirectAttributes flash) {

        //Evitando que pongan un id del profesor incorrecto
        if(subject.getTeacher() == null) {
            flash.addFlashAttribute(ERROR, "Id del profesor incorecto");
            return "redirect:/ver/" + subject.getId();
        }

        subjectDao.save(subject);
        flash.addFlashAttribute(SUCCESS, "Editado con éxito");

        return "redirect:/ver/" + subject.getId();
    }
}
