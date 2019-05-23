package controllers;

import model.DegreeOfLector;
import model.Department;
import model.Lector;
import org.hibernate.Query;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class InputController{

    public static Optional<Department> findDepartmentByName(String departmentName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Query q = session.createQuery("from Department d where d.name=:name")
                .setParameter("name", departmentName);

        Optional<Department> department = Optional.ofNullable((Department) q.uniqueResult());

        session.getTransaction().commit();
        return department;

    }

    public static void headOfDepartment(String departmentName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Department head = (Department) session.createQuery("from Department d where d.name=:name")
                .setParameter("name", departmentName).uniqueResult();
        if (!head.equals(null)){
        System.out.println("The head of department is " + head.getHeadOfDepartment());}
        else
            System.out.println("Wrong input");
        session.getTransaction().commit();
    }

    public static void departmentStatistics(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        if (department.isPresent()){
        Query q = session.createQuery("select count(l.name) " +
                "from Lector l where l.degree=:degree and :department in elements(l.departments)");
        Long assistantsCount = (Long) q.setParameter("degree", DegreeOfLector.ROLE_ASSISTANT)
                .setParameter("department", department.get()).uniqueResult();
        Long assosiateProfessorsCount = (Long) q.setParameter("degree", DegreeOfLector.ROLE_ASSOCIATE_PROFESSOR)
                .setParameter("department", department.get()).uniqueResult();
        Long professorsCount = (Long) q.setParameter("degree", DegreeOfLector.ROLE_PROFESSOR)
                .setParameter("department", department.get()).uniqueResult();

        session.getTransaction().commit();

        System.out.println("Department statistics" + departmentName);
        System.out.println("ROLE_ASSISTANT: " + assistantsCount);
        System.out.println("ROLE_ASSOCIATE_PROFESSOR: " + assosiateProfessorsCount);
        System.out.println("ROLE_PROFESSOR: " + professorsCount);
        }else
            System.out.println("Wrong input");
    }

    public static void averageSalary(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        if (department.isPresent()){
        Query q = session.createQuery("select avg(l.salary) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
        Double avgSalary = (Double) q.uniqueResult();

        session.getTransaction().commit();
        System.out.println("The average salary for department " + departmentName + ": " + avgSalary);
        }else
            System.out.println("Wrong input");
    }


    public static void countOfEmployee(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        if (department.isPresent()) {
            Query q = session.createQuery("select count(l) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
            Long count = (Long) q.uniqueResult();

            session.getTransaction().commit();
            System.out.println("Count of employee of department " + departmentName + ": " + count);
        } else
            System.out.println("Wrong input");
    }

    public static void globalSearch(String word) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        Query q = session.createQuery("from Lector l where l.name like :word")
                .setParameter("word", "%" + word.toLowerCase() + "%");
        List<Lector> lectors = q.list();

        if (lectors.isEmpty()){
            System.out.println("No such element");
        }else
            System.out.println("Answer:");
            for (Lector l : lectors) {
                System.out.println(l.getName());
            }
        session.getTransaction().commit();
    }
    }
