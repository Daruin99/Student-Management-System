package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Dao.TeacherRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import com.Abdelrahman.studentmanagementsystem.Exception.InvalidPasswordException;
import com.Abdelrahman.studentmanagementsystem.Exception.UserNameExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class TeacherServiceImp implements TeacherService{

    private TeacherRepo teacherRepo;
    private PasswordEncoder passwordEncoder;

    public TeacherServiceImp(TeacherRepo teacherRepo, PasswordEncoder passwordEncoder) {
        this.teacherRepo = teacherRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Teacher findTeacherByUserName(String userName) {
        Teacher teacher = new Teacher();
        return teacherRepo.findByUserName(userName);
    }

    @Override
    public void createTeacher(Teacher teacher) throws InvalidPasswordException, UserNameExistsException {
        if (!isPasswordStrong(teacher.getPassword())) {
            throw new InvalidPasswordException("Weak password");
        }

        if (doesUserNameExist(teacher.getUserName())) {
            throw new UserNameExistsException("User name exists");
        }
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepo.save(teacher);
    }

    private boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}");
    }

    private Boolean doesUserNameExist(String userName) {
        Teacher teacher = teacherRepo.findByUserName(userName);
        return teacher != null;
    }
}
