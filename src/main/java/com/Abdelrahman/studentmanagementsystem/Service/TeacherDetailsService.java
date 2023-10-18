package com.Abdelrahman.studentmanagementsystem.Service;

import com.Abdelrahman.studentmanagementsystem.Dao.TeacherRepo;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherDetailsService implements UserDetailsService {

    private TeacherServiceImp teacherServiceImp;

    public TeacherDetailsService(TeacherServiceImp teacherServiceImp) {
        this.teacherServiceImp = teacherServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Teacher> teacher = teacherServiceImp.findByUserName(username);
        if(teacher.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new TeacherPrincipal(teacher.get());
    }
}
