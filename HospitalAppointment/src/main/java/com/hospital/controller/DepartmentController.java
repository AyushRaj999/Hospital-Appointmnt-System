package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Department>> createDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestBody Department department, @PathVariable Long id){
        return departmentService.updateDepartment(department,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Department>> deletedepartment(@PathVariable Long id){
        return departmentService.DeleteDepartment(id);
    }

    @GetMapping("/department/{departmentName}")
    public ResponseEntity<ResponseStructure<Department>> getByDepartmentName(@PathVariable String departmentName){
        return departmentService.getDepartmentByName(departmentName);
    }
}
