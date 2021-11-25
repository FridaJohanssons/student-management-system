package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject){
        entityManager.persist(subject);
    }

    public List<Subject> getAllSubjects(){
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

}