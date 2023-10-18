package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, RedirectAttributes redirectAttributes) {
        Optional<Teacher> dataBaseTeacher = teacherService.findByUserName(teacher.getUserName());
        if (dataBaseTeacher.isEmpty()) {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacherService.save(teacher);
            redirectAttributes.addAttribute("success", "You have successfuly registred");
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("registerError","user name exists");
        return "redirect:/register";
    }


}
