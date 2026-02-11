package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Prescription;
import com.hospital.service.PrescriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
@AllArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    // Create
    @PostMapping
    public ResponseEntity<ResponseStructure<Prescription>> create(@RequestBody Prescription prescription){
        return service.createPrescription(prescription);
    }

    // All
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Prescription>>> getAll(){
        return service.getAll();
    }

    // By ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Prescription>> getById(@PathVariable Long id){
        return service.getById(id);
    }

    // By Medical Record
    @GetMapping("/record/{recordId}")
    public ResponseEntity<ResponseStructure<Prescription>> getByRecord(@PathVariable Long recordId){
        return service.getByRecord(recordId);
    }

    // By Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ResponseStructure<List<Prescription>>> getByPatient(@PathVariable Long patientId){
        return service.getByPatient(patientId);
    }
}

