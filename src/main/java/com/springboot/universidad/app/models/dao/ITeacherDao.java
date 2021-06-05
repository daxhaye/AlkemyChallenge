package com.springboot.universidad.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.universidad.app.models.entity.Teacher;

public interface ITeacherDao extends CrudRepository<Teacher, Long> {

    @Query("select c from Teacher c left join fetch c.materiasDadas f where c.id=?1")
    public Teacher fetchByIdWithMateriasDadas(Long id);


}
