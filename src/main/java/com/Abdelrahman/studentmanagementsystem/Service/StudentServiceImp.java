package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Dao.StudentRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    private StudentRepo studentRepo;

    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Student findById(int id) {

        Optional<Student> result =  studentRepo.findById(id);
        Student theStudent = null;

        if(result.isPresent()) {

            theStudent = result.get();
        } else {
            throw  new RuntimeException("This student doesnt exist");
        }
        return  theStudent;
    }

    @Override
    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
}
