package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface TeacherService {
    Optional<Teacher> findByUserName(String userName);

    void save(Teacher teacher);
}
