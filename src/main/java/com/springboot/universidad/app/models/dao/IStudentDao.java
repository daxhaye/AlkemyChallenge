package com.springboot.universidad.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.universidad.app.models.entity.Student;

public interface IStudentDao extends CrudRepository<Student, Long> {

    public Student findByUsername(String username);
}
