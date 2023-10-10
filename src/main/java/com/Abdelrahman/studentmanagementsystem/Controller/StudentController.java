package com.Abdelrahman.studentmanagementsystem.Controller;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addForm(Model theModel) {
        Student student = new Student();
        theModel.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student student) {
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
        return "display-students";
    }
}
