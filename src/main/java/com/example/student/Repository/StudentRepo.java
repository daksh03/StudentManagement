package com.example.student.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.student.Model.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, String> , PagingAndSortingRepository<Student,String> {


}
