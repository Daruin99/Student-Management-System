package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Exception.InvalidPasswordException;
import com.Abdelrahman.studentmanagementsystem.Exception.UserNameExistsException;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.Optional;

@Controller
public class RegistrationController {

    private TeacherService teacherService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(TeacherService teacherService, PasswordEncoder passwordEncoder) {
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(Model theModel) {
        theModel.addAttribute("teacher", new Teacher());
        return "register";
    }

    @PostMapping("/register")
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            teacherService.createTeacher(teacher);
        } catch (UserNameExistsException e) {
            redirectAttributes.addFlashAttribute("error", "user name exists");
            return "redirect:/register";
        } catch (InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("passwordError", "wrong pass");
            return "redirect:/register";
        }
        redirectAttributes.addFlashAttribute("success", "You have successfuly registred");
        return "redirect:/showLogInPage";


    }


}
