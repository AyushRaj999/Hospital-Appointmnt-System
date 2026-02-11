package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Patient>> addPatient(@RequestBody Patient patient){
        return patientService.registerPatient(patient);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatient(){
        return patientService.getAllPatient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Patient>> getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/patient/{phoneNumber}")
    public ResponseEntity<ResponseStructure<Patient>> getPatientByPhoneNumber(@PathVariable String phoneNumber){
        return patientService.getPatientByPhoneNumber(phoneNumber);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatentAgeGreaterThan(@PathVariable Integer age){
        return patientService.getPatientByAge(age);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatientByAppointment(@PathVariable LocalDateTime date){
        return patientService.getPatientByAppointmentDate(date);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestBody Patient patient, @PathVariable Long id){
        return patientService.updatePatient(patient,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Patient>> deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }

    @GetMapping("/medical/{recordId}")
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatientByMedicalRecord(@PathVariable Long recordId){
        return patientService.getPatientByMedicalRecord(recordId);
    }
}
