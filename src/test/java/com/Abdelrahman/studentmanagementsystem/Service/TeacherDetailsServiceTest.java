package com.Abdelrahman.studentmanagementsystem.Service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import com.Abdelrahman.studentmanagementsystem.Entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@ExtendWith(MockitoExtension.class)

@ExtendWith(MockitoExtension.class)
public class TeacherDetailsServiceTest {
    @Mock private TeacherServiceImp teacherServiceImp;
    private TeacherDetailsService underTest;

    @BeforeEach
    void setup() {
        underTest = new TeacherDetailsService(teacherServiceImp);
    }

    @Test
    void canLoadUserByUsername() {
        // given
        Teacher teacher = new Teacher();
        teacher.setUserName("UniqueUserName");
        when(teacherServiceImp.findTeacherByUserName(teacher.getUserName())).thenReturn(teacher);

        // when
        UserDetails userDetails = underTest.loadUserByUsername(teacher.getUserName());

        // then
        assertThat(userDetails.getUsername()).isEqualTo(teacher.getUserName());
    }

    @Test
    void willThrowWhenUsernameNotFound() {
        // given
        String nonExistentUsername = "NonExistentUserName";
        when(teacherServiceImp.findTeacherByUserName(nonExistentUsername)).thenReturn(null);

        // then
        assertThrows(UsernameNotFoundException.class, () -> {
            // when
            underTest.loadUserByUsername(nonExistentUsername);
        });
    }
}

