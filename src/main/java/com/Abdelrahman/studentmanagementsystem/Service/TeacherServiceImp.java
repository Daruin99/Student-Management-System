package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Dao.TeacherRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class TeacherServiceImp implements TeacherService{

    private TeacherRepo teacherRepo;

    public TeacherServiceImp(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public Optional<Teacher> findByUserName(String userName) {

        return teacherRepo.findByUserName(userName);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepo.save(teacher);
    }
}
