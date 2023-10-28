package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Service.StudentService;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherPrincipal;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Security;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private static TeacherService teacherService;

    public StudentController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        StudentController.teacherService = teacherService;
    }

    private static String getLoggedInName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private static Teacher getLoggedInTeacher(String userName) {
        return teacherService.findTeacherByUserName(userName);
    }

    @GetMapping("/list")
    public String displayStudents(Model theModel) {

        List<Student> students = studentService.findByTeacher(StudentController.getLoggedInTeacher(getLoggedInName()));
        theModel.addAttribute("students", students);
        return "display-students";
    }

    @GetMapping("/showFormForAdd")
    public String addForm(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "student-form";
        }

        Teacher teacher = getLoggedInTeacher(getLoggedInName());
        student.setTeacher(teacher);

        try {
            studentService.createStudent(student);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Email exists");
            return "redirect:/students/showFormForAdd";
        }
        return "redirect:/students/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateForm(@RequestParam("studentId") int studentId, Model theModel) {
        Student theStudent = studentService.findStudentById(studentId);
        theModel.addAttribute("student", theStudent);
        return "student-form";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("studentId") int studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/students/list";
    }
}
