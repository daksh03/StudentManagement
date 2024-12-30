package com.example.student.Service;

import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeststudentServiceImpl {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("1", "Daksh", 24, "A+", "Choudhary Puram");
    }

    @Test
    void testGetAllStudents() {
        Page<Student> students = new PageImpl<>(List.of(student));
        given(studentRepo.findAll(any(PageRequest.class))).willReturn(students);

        Page<Student> result = studentService.getAllStudents(0, 10);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Daksh");
    }

    @Test
    void testGetStudentById() {
        given(studentRepo.findById(anyString())).willReturn(Optional.of(student));

        Student result = studentService.getStudentbyId("1");

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Daksh");
    }

    @Test
    void testAddStudent() {
        given(studentRepo.save(any(Student.class))).willReturn(student);

        Student result = studentService.addStudent(student);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Daksh");
    }

    @Test
    void testUpdateStudent() {
        given(studentRepo.findById(anyString())).willReturn(Optional.of(student));
        given(studentRepo.save(any(Student.class))).willReturn(student);

        Student updatedStudent = new Student("1", "Daksh Updated", 25, "A", "New Address");
        Student result = studentService.updateStudent("1", updatedStudent);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Daksh Updated");
        assertThat(result.getAge()).isEqualTo(25);
    }

    @Test
     void testDeleteStudent() { 
        given(studentRepo.findById(anyString()))
        .willReturn(Optional.of(student));
         studentService.deleteStudent("1");
          verify(studentRepo).delete(student);
     }
}
