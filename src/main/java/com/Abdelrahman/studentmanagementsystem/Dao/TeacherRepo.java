package com.Abdelrahman.studentmanagementsystem.Dao;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher,String> {
    public Teacher findByUserName(String userName);
}
