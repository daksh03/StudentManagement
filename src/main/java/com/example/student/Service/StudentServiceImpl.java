package com.example.student.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepo studentRepo;

    @Override
    @Cacheable(value = "students",key = "{#page,#size}")
    public Page<Student> getAllStudents(int page, int size) {
        logger.info("fetching all students with page : {} and size : {}",page,size);
        return studentRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    @Cacheable(value = "students",key = "#id")
    public Student getStudentbyId(String id) {
        logger.info("Fetching student with id : {}",id);
        return studentRepo.findById(id)
            .orElseThrow(() -> {
                logger.error("Student not found with id : {}", id);
            return new RuntimeException("Student not found with id "+id);
            });
    }

    @Override
    @CachePut(value = "students",key = "#student.id")
    public Student addStudent(Student student) {
        logger.info("Adding Student :{}",student);
        if (studentRepo.existsById(student.getId())) {
            logger.error("A student with id : {} exists",student.getId());
            throw new IllegalArgumentException("A student with this ID already exists!");
        }
        return studentRepo.save(student);
    }

    @Override
    @CachePut(value = "students",key = "#id")
    public Student updateStudent(String id, Student student) {
        logger.info("Update student with id: {}",id);
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

        logger.info("Updated Student with id :{}", id);
        logger.info("Updated Student :{}", stud);
        return studentRepo.save(stud);
    }

    @Override
    @CacheEvict(value = "students",key = "#id")
    public void deleteStudent(String id) {
        logger.info("Deleting Student with id :{}",id);
        Student student = getStudentbyId(id);
        studentRepo.delete(student);
        logger.info("Deleted Student with id :{}", id);
    }
}
