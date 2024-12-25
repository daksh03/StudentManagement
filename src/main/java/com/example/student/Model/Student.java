package com.example.student.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String grade;
    private String address;
    
    public Student(){

    }

    public Student(String name ,int age, String grade, String address) {
        this.name=name;
        this.age=age;
        this.grade=grade;
        this.address=address;
    }
}
