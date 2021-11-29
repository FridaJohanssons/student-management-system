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

        Subject subject1 = new Subject("Mathematics");
        Subject subject2 = new Subject("Art");
        Subject subject3 = new Subject("Biology");

        Teacher teacher1 = new Teacher("Bill", "Andersson", "bio@email");
        Teacher teacher2 = new Teacher("Ally", "Ward", "ally@mail");

        Student student1 = new Student("Lukas", "Smith", "lukas@.com", "070217");
        Student student2 = new Student("Albin", "Jonson", "a@mail.com", "070333");
        Student student3 = new Student("Moa", "Acker", "moa@email", "078323");
        Student student4 = new Student("Anna", "Miller", "anna@.com", "0704444");

        teacher1.addSubject(subject1);
        teacher2.addSubject(subject2);
        teacher1.addSubject(subject3);

        subject1.addStudent(student1);
        student1.addSubject(subject1);
        subject2.addStudent(student1);
        subject2.addStudent(student2);

        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student2.addSubject(subject2);
        student3.addSubject(subject3);
        student4.addSubject(subject3);
        student3.addSubject(subject1);

        entityManager.persist(teacher1);
        entityManager.persist(subject1);
        entityManager.persist(student1);

        entityManager.persist(teacher2);
        entityManager.persist(subject2);
        entityManager.persist(student2);

        entityManager.persist(subject3);
        entityManager.persist(student3);

        entityManager.persist(student4);
    }
}
