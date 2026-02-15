package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Doctor>> addDoctors(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctors(){
        return doctorService.getAllDoctor();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseStructure<Doctor>> getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<ResponseStructure<Doctor>> getDoctorBySpecialization(@PathVariable String specialization){
        return doctorService.getDoctorBySpecialization(specialization);
    }

    @GetMapping("/department/{departmentName}")
    public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByDepartment(@PathVariable String departmentName){
        return doctorService.getAllDoctorByDepartment(departmentName);
    }

    @GetMapping("/patient/{patient}")
    public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByPatientName(@PathVariable String patient){
        return doctorService.getAllDoctorByPatient(patient);
    }

    @GetMapping("/appointment/{appointment}")
    public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByAppointment(@PathVariable LocalDateTime appointment){
        return doctorService.getAllDoctorByAppointment(appointment);
    }

    @GetMapping("/availability/{availability}")
    public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByAvailability(@PathVariable String availability){
        return doctorService.getAllDoctorByAvailableDays(availability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor,@PathVariable Long id){
        return doctorService.updateDoctors(doctor, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Doctor>> deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }
}
