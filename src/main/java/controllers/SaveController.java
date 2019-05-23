package controllers;

import model.DegreeOfLector;
import model.Department;
import model.Lector;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class SaveController {

    public static void saveAllModels() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Set<Lector> lectors1 = new HashSet<>();
        Set<Lector> lectors2 = new HashSet<>();
        Set<Lector> lectors3 = new HashSet<>();

        Department department1 = new Department("Faculty of Law", "Stark");
        Department department2 = new Department("Faculty of Biology", "Albert Einstein");
        Department department3 = new Department("Faculty of Economics", "Aristarchus");

        lectors1.add(new Lector("Aristarchus",1000, DegreeOfLector.ROLE_ASSISTANT));
        lectors1.add(new Lector("Aristotle",1500, DegreeOfLector.ROLE_ASSOCIATE_PROFESSOR));
        lectors2.add(new Lector("Albert Einstein",2000, DegreeOfLector.ROLE_PROFESSOR));
        lectors2.add(new Lector("Aristarch", 1200, DegreeOfLector.ROLE_ASSISTANT));
        lectors2.add(new Lector("Aris", 1300 ,DegreeOfLector.ROLE_ASSOCIATE_PROFESSOR));
        lectors3.add(new Lector("Stk", 1200, DegreeOfLector.ROLE_ASSISTANT));
        lectors3.add(new Lector("Ari", 1200, DegreeOfLector.ROLE_ASSISTANT));
        lectors3.add(new Lector("Stark", 500, DegreeOfLector.ROLE_ASSISTANT));

        department1.setLectors(lectors1);
        department2.setLectors(lectors2);
        department3.setLectors(lectors3);

        session.persist(department1);
        session.persist(department2);
        session.persist(department3);

        session.getTransaction().commit();
    }
}
