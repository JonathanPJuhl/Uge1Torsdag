/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;

/**
 *
 * @author Jonathan
 */
public class EmployeeDTO {
    int id;
    String name;
    String adress;

    public EmployeeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();
        this.adress = emp.getAddress();
    }
    
}
