package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student){
        entityManager.persist(student);
    }

    public List<Student> getAllStudents(){
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public Student findStudent(Long id){
        return entityManager.find(Student.class, id);
    }
}
