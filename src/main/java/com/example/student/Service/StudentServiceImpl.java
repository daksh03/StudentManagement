package com.example.student.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentbyId(String id) {
        return studentRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id "+id));
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }
    
    @Override
    public Student updateStudent(String id, Student student) {
        Student stud = getStudentbyId(id);
        if (student.getName() != null) {
            stud.setName(student.getName());
        }
        if (student.getAge() != null) {
            stud.setAge(student.getAge());
        }
        if (student.getGrade() != null) {
            stud.setGrade(student.getGrade());
        }
        if (student.getAddress() != null) {
            stud.setAddress(student.getAddress());
        }
        return studentRepo.save(stud);
    }

    @Override
    public void deleteStudent(String id) {
        Student student = getStudentbyId(id);
        studentRepo.delete(student);
    }
}
