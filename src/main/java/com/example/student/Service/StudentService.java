package com.example.student.Service;

import java.util.List;

import com.example.student.Model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentbyId(String id);
    Student addStudent(Student student);
    Student updateStudent(String id ,Student student);
    void deleteStudent(String id);
}
