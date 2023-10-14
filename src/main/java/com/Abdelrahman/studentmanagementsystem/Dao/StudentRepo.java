package com.Abdelrahman.studentmanagementsystem.Dao;

import com.Abdelrahman.studentmanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
