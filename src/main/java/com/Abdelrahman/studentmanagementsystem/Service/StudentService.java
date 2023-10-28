package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    void createStudent(Student student);

    Student findStudentById(int id);
    void deleteStudentById(int id);

    public List<Student> findByTeacher(Teacher teacher);
}
