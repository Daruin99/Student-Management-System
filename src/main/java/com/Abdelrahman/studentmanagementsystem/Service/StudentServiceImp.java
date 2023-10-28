package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Dao.StudentRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    private StudentRepo studentRepo;

    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void createStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public void deleteStudentById(int id) {

        studentRepo.deleteById(id);
    }


    @Override
    public List<Student> findByTeacher(Teacher teacher) {
        return studentRepo.findByTeacher(teacher);
    }
}
