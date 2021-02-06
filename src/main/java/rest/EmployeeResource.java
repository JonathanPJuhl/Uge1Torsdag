/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Jonathan
 */
@Path("employee")
public class EmployeeResource {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade em = EmployeeFacade.getEmployeeFacade(emf);
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
      List<Employee> emp = em.getAllEmployees();
      if(emp!=null){
      return new Gson().toJson(emp);}
      else{
          return null;
      }
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2(@PathParam("id") int id) {
     
      Employee emp = em.findEmployeeById(id);
      if(emp!=null){
      return new Gson().toJson(emp);}
      else{
          return null;
      }
   
   
        
    }
    @GET
    @Path("highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        em.getEmployeeWithHighestSalary();
        Employee emp =em.getEmployeeWithHighestSalary();
      if(emp!=null){
      return new Gson().toJson(emp);}
      else{
          return null;
      }
   
    }
    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson4(@PathParam("name") String name) {
      List<Employee> emp = em.findEmployeeByName(name);
      if(emp!=null){
      return new Gson().toJson(emp);}
      else{
          return null;
      }
    }
    
    
    /**
     * PUT method for updating or creating an instance of EmployeeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
