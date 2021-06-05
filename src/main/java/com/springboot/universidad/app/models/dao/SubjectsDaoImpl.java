package com.springboot.universidad.app.models.dao;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.universidad.app.models.entity.Subjects;

@Repository
public class SubjectsDaoImpl implements ISubjectsDao {


    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Subjects> findAll() {
        List<Subjects> subjects = em.createQuery("from Subjects").getResultList();

        subjects.sort(Comparator.comparing(Subjects::getName));
        return subjects;
    }

    @Override
    @Transactional(readOnly = true)
    public Subjects findOne(int id) {
        return em.find(Subjects.class, id);
    }

    @Override
    @Transactional
    public void save(Subjects subject) {
        if (subject.getId() > 0) {
            em.merge(subject);
        } else {
            em.persist(subject);
        }
    }
}
