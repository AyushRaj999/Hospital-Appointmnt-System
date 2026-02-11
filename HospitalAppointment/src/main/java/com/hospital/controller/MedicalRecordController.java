package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.MedicalRecord;
import com.hospital.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService service;

    // Create Record
    @PostMapping
    public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(@RequestBody MedicalRecord record){
        return service.createRecord(record);
    }

    // Fetch All Medical record
    @GetMapping
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAll(){
        return service.getAllRecords();
    }

    //Fetch Medical Record By ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<MedicalRecord>> byId(@PathVariable Long id){
        return service.getRecordById(id);
    }

    //Fetch Medical Record By Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> byPatient(@PathVariable Long patientId){
        return service.getRecordByPatient(patientId);
    }

    //Fetch Medical Record By Doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> byDoctor(@PathVariable Long doctorId){
        return service.getRecordByDoctor(doctorId);
    }

    //Fetch Medical Record By Visit Date
    @GetMapping("/date/{date}")
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> byDate(@PathVariable LocalDate date){
        return service.getRecordByVisitDate(date);
    }
}

