package com.springboot.universidad.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.universidad.app.models.entity.Teacher;
import com.springboot.universidad.app.models.services.ITeacherServices;

@Controller
public class TeacherController {

    private final static String TITLE = "title";
    private final static String TEACHER = "teacher";

    private final static String ERROR = "error";
    private final static String SUCCESS = "success";

    @Autowired
    private ITeacherServices teacherServices;

    @Secured("ROLE_USER")
    @GetMapping("/profesores")
    public String listar(Model model) {
        model.addAttribute(TITLE, "Listado de Profesores");
        model.addAttribute(TEACHER, teacherServices.findAll());

        return "profesores";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Teacher teacher;

        if (id <= 0) {
            flash.addFlashAttribute(ERROR, "El Id del profesor no puede ser cero!");
            return "redirect:/profesores";
        }


        teacher = teacherServices.findOne(id);
        if (teacher == null) {
            flash.addFlashAttribute(ERROR, "El Id del Profesor no existe en la BBDD!");
            return "redirect:/profesores";
        }

        model.addAttribute(TEACHER, teacher);
        model.addAttribute(TITLE, "Editar cliente");
        return "form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Teacher teacher, Model model, RedirectAttributes flash, SessionStatus status) {

        teacherServices.save(teacher);
        status.setComplete();
        flash.addFlashAttribute(SUCCESS, "Editado con éxito");
        return "redirect:/profesores";
    }

}
