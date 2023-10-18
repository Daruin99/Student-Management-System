package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    void save(Student student);

    Student findById(int id);
    void deleteById(int id);
    List<Student> findAll();

    public List<Student> findByTeacher(Teacher teacher);
}
