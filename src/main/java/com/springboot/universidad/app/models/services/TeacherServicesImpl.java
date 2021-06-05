package com.springboot.universidad.app.models.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.universidad.app.models.dao.ITeacherDao;
import com.springboot.universidad.app.models.entity.Teacher;

@Repository
public class TeacherServicesImpl implements ITeacherServices {

    @Autowired
    private ITeacherDao teacherDao;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll() {
        return (List<Teacher>) teacherDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findOne(Long id) {
        return teacherDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

}
