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
        Subject subject2 = new Subject("Art");

        Teacher teacher1 = new Teacher("name", "last", "email");
        Teacher teacher2 = new Teacher("Ally", "Jsson", "ally@mail");

        Student student1 = new Student("Name", "last", "email", "number");
        Student student2 = new Student("Albin", "Hallvarsson", "a@mail.com", "070333");

        teacher1.addSubject(subject1);
        subject1.addStudent(student1);
        student1.addSubject(subject1);

        subject2.addStudent(student1);
        subject2.addStudent(student2);


        teacher2.addSubject(subject2);

        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student2.addSubject(subject2);

        entityManager.persist(teacher1);
        entityManager.persist(subject1);
        entityManager.persist(student1);

        entityManager.persist(teacher2);
        entityManager.persist(subject2);
        entityManager.persist(student2);
    }
}
