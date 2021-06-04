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
    // Clave el uso de constantes
    private final static String ERROR = "Error";

    @Autowired
    private ISubjectsDao subjectDao;

    @Autowired
    private IStudentDao studentDao;

    @GetMapping("/inscribirse/{id}")
    public String inscribirse(@PathVariable(value = "id") int id, RedirectAttributes flash, Principal principal) {
        String result = "redirect:/ver/" + id;
        // La iniciación de variables en null olvidate. Objeto loquesea = null, solo escribi Objeto loquesea
        Student alumno;

        // Esto se llama fail fast.. lo primero que haces es devolver el caso de error
        // Entonces despues el caso principal es mas facil de leer ;)
        if (principal == null) {
            flash.addFlashAttribute(ERROR, "No ha iniciado Sesión");

            return result;
        }

        alumno = studentDao.findByUsername(principal.getName());

        Subjects materia;
        // De nuevo, fail fast. Primero el caso de error
        if (id <= 0) {
            flash.addFlashAttribute(ERROR, "El Id no puede ser 0");

            return result;
        }

        materia = subjectDao.findOne(id);
        // Evita SIEMPRE las logicas negadas. Es muy propenso a pegarte un tiro en las pelotas.
        // Ahora porque tenes el codigo muy fresquito. Despues cuando lo leas en 2 meses no vas a entender una chota
        // Esto se asemeja un poco al tema de "fail fast"
        if (alumno.getMaterias().contains(materia) && materia.getCupAlumn() > 0) {
            String mensajeFlash = (alumno.getMaterias().contains(materia)) ? "Ya estas inscripto en la materia" : "Cupo lleno";
            flash.addFlashAttribute("error", mensajeFlash);
        } else {
            materia.decCupAlumn();
            alumno.addMaterias(materia);
            studentDao.save(alumno);
            subjectDao.save(materia);
            // La concatenacion de strings no se hace NUNCA con el + (en ningun lenguaje)
            flash.addFlashAttribute("success", String.format("Te inscribiste a %s correctamente!", materia.getName()));
        }

        return result;
        // No me gusta como quedo esto de "return result" por todos lados, pero era para mostrarte un poco como es la bocha
    }
}

// Por favor te lo pido por lo que mas quieras en el mundo.
// NUNCA uses tabs, siempre espacios ;) y siempre trata de formatear bien los archivos
// existe algo que se llama "linter" que te autoformatea los archivos en casi todos los editores de texto
// por ejemplo en intellij se usa "ctrl + shift + l" y lo corre solito nomas
// basicamente lo que hace es pasar de "if(davidEsGay.equals("noSabeUsarLinter)){
// a algo como "if (davidEsGay.equals("noSabeUsarLinter")) { ...... los espacios son claves ;)
