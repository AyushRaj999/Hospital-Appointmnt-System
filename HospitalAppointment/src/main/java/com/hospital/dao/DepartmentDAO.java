package com.hospital.dao;

import com.hospital.entity.Department;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NameNotFound;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDAO{
    @Autowired
    private DepartmentRepository departmentRepository;

    // Create Department
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    //Fetch All Department
    public List<Department> getAllDepartment(){
        List<Department> departments = departmentRepository.findAll();
        if(!departments.isEmpty()){
            return departments;
        }
        else{
            throw new NoRecordAvailableException("No Record are available in Database");
        }
    }

    //Fetch All Department By Id
    public Department getDepartmentById(Long id){
        Optional<Department> optional = departmentRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new IdNotFoundException("Id Not Found");
        }
    }

    //Update department
    public Department updateDepartment(Department department, Long id){
        if (id==null){
            throw new IdNotFoundException("Please enter Id");
        }
        Department exist = departmentRepository.findById(id)
                .orElseThrow(()->new IdNotFoundException("Id not found for update :"+id));
        exist.setDepartmentName(department.getDepartmentName());
//        System.out.println("Old: " + exist.getDepartmentName());
//        System.out.println("New: " + department.getDepartmentName());

        return departmentRepository.save(exist);
    }

    //Delete Department
    public void deleteDepartment(Long id){
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()){
            departmentRepository.delete(optional.get());
        }
        else {
            throw new IdNotFoundException("Id Not Found In Database");
        }
    }

    //Find By Department Name
    public Department findByDepartmentName(String departmentName) {
        Optional<Department> optional = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new NameNotFound(departmentName+" not present in Database");
        }
    }
}
