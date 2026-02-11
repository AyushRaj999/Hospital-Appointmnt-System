package com.hospital.service;

import com.hospital.dao.DepartmentDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDAO departmentDAO;

    // Save Department
    public ResponseEntity<ResponseStructure<Department>> saveDepartment(Department department){
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Department record saved Successfully");
        response.setData(departmentDAO.saveDepartment(department));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Fetch All Department
    public ResponseEntity<ResponseStructure<List<Department>>> getAllDepartment(){
        ResponseStructure<List<Department>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All Department get Successfully");
        response.setData(departmentDAO.getAllDepartment());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Fetch department By Id
    public ResponseEntity<ResponseStructure<Department>> getDepartmentById(Long id){
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Get Department With id: "+id);
        response.setData(departmentDAO.getDepartmentById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Update Department
    public ResponseEntity<ResponseStructure<Department>> updateDepartment(Department department, Long id){
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Department Update Successfully with id: "+id);
        response.setData(departmentDAO.updateDepartment(department,id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Delete Department
    public ResponseEntity<ResponseStructure<Department>> DeleteDepartment(Long id){
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Delete Department With id: "+id);
//        response.setData("Su");
        departmentDAO.deleteDepartment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Fetch Department By Department Name
    public ResponseEntity<ResponseStructure<Department>> getDepartmentByName(String departmentName){
        ResponseStructure<Department> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Get Department With Name: "+departmentName);
        response.setData(departmentDAO.findByDepartmentName(departmentName));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
