package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {
    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData(){

        Subject subject1 = new Subject("SubjectnameTexMath");

        Teacher teacher1 = new Teacher("name", "last", "email");

        Student student1 = new Student("Name", "last", "email", "number");

        teacher1.addSubject(subject1);
        subject1.addStudent(student1);
        student1.addSubject(subject1);

        entityManager.persist(teacher1);
        entityManager.persist(subject1);
        entityManager.persist(student1);
    }
}
