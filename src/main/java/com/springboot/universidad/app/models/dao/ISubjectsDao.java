package com.springboot.universidad.app.models.dao;

import java.util.List;

import com.springboot.universidad.app.models.entity.Subjects;

public interface ISubjectsDao {

	public List<Subjects> findAll();
	
	public Subjects findOne(int id);
	
	public void save(Subjects subject);
	

}
