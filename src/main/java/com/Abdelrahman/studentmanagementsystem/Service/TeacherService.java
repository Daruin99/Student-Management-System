package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;



public interface TeacherService {
    Teacher findTeacherByUserName(String userName);

    void createTeacher(Teacher teacher) throws Exception;
}
