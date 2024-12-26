package com.example.student.Service;

import org.springframework.data.domain.Page;

import com.example.student.Model.Student;

public interface StudentService {
    Page<Student> getAllStudents(int page , int size);
    Student getStudentbyId(String id);
    Student addStudent(Student student);
    Student updateStudent(String id ,Student student);
    void deleteStudent(String id);
}
