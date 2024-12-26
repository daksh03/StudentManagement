package com.example.student.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter
@Document(collection = "students")
public class Student {
    @Id
    @Max(value = 100,message = "Maximum id is 100")
    @Indexed(unique = true)
    private String id;

    @NotBlank(message = "Name is Mandotary")
    private String name;

    @Min(value = 3,message = "Minimum age should be 3")
    private Integer age;
    private String grade;

    @NotBlank(message = "Address is mandotary")
    private String address;
    
    public Student(){

    }

    public Student(String id,String name ,int age, String grade, String address) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.grade=grade;
        this.address=address;
    }
}
