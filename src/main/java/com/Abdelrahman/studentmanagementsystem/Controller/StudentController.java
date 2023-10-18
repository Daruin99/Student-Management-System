package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Service.StudentService;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherPrincipal;
import com.Abdelrahman.studentmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        Optional<Teacher> teacher = teacherService.findByUserName(userName);
        return teacher.get();
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
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "student-form";
        }

        Teacher teacher = getLoggedInTeacher(getLoggedInName());
        student.setTeacher(teacher);

        studentService.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateForm(@RequestParam("studentId") int studentId, Model theModel) {
        Student theStudent = studentService.findById(studentId);
        theModel.addAttribute("student", theStudent);
        return "student-form";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("studentId") int studentId) {
        studentService.deleteById(studentId);
        return "redirect:/students/list";
    }
}
