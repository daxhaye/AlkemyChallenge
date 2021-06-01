package com.springboot.universidad.app.models.services;

import java.util.List;

import com.springboot.universidad.app.models.entity.Teacher;

public interface ITeacherServices {
	
	public List<Teacher> findAll();
	
	public Teacher findOne(Long id);
	
	public void save(Teacher teacher);

}
