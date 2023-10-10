package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String displayStudents(Model theModel) {
        List<Student> students = studentService.findAll();
        theModel.addAttribute("students", students);
        return "display-students";
    }

    @GetMapping("/showFormForAdd")
    public String displayForm(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students/list";
    }
}
