package facades;

import entities.Employee;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class);
//        List<Employee> employeeList=employees.getResultList();
//        
//        for(int i=0; i<employeeList.size()-1; i++){
//            Employee e = em.find(Employee.class, i);
//            em.remove(e);
//            
//        }
//                 EntityManager em = emf.createEntityManager();
//        try {
//             em.getTransaction().begin();
//            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new Employee("Some txt", "More text", 1234));
            em.persist(new Employee("aaa", "bbb", 123));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        
         EntityManager em = emf.createEntityManager();
        try {
             em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        }finally{
            em.close();
        }
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testOfAddEmployeeAndFindEmployee() {
        Employee actual = facade.addEmployee("Jørn", "Havnemolen 4", 2000);
        Employee expected = facade.findEmployeeById(actual.getId());
        assertEquals( expected, actual, "Expects the same output of employee from the database");
   
    }
    @Test
    public void testOfGetAllEmployees() {
        List<Employee> list = facade.getAllEmployees();
        int actual = list.size();
        int expected = 2;
        assertEquals( expected, actual, "Expects the same output of nr of employees from the database");
   
    }
     @Test
    public void testOfFindEmployeeByName() {
        List<Employee> list = facade.findEmployeeByName("aaa");
        int actual = list.size();
        int expected = 1;
        assertEquals( expected, actual, "Expects the same output of nr of employees from the database");
   
    }
    @Test
    public void testOfFindEmployeeByHighestSalary() {
        Employee actual = facade.getEmployeeWithHighestSalary();
        List<Employee> list = facade.findEmployeeByName("Some txt");
        Employee expected = list.get(0);
        
        assertEquals( expected, actual, "Expects the same output of nr of employees from the database");
   
    }

}
