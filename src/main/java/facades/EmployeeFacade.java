/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.QueryException;

/**
 *
 * @author Jonathan
 */

public class EmployeeFacade {
private static EntityManagerFactory emf;
private static EmployeeFacade instance;

    public EmployeeFacade() {
    }

   

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public Employee addEmployee(String name, String adress, double salary){
        Employee employee = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    public int clearAndCreateEmployeeTable(){
        EntityManager em = emf.createEntityManager();
        try{
           
            em.createQuery("DROP DATABASE Week1Day4");
            em.createQuery("CREATE DATABASE Week1Day4");
            return 1;
        }finally {
            em.close();
        }
    }
    
    public Employee findEmployeeById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            return employee;
        }finally {
            em.close();
        }
    }
        public List<Employee> findEmployeeByName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e WHERE e.name=:name1", Employee.class);
            q1.setParameter("name1", name);
            return q1.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Employee> getAllEmployees(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e from Employee e",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public Employee getEmployeeWithHighestSalary(){
        EntityManager em = emf.createEntityManager();
        try{
        TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
        return q1.getSingleResult();
    }finally{
            em.close();
        }
}
    }

