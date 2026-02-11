package com.hospital.controller;

import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Appointment;
import com.hospital.enu.Status;
import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Book Appointment
    @PostMapping
    public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(@RequestBody Appointment appointment){

        return appointmentService.bookAppointment(appointment);
    }

    // Fetch All Appointments
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments(){

        return appointmentService.getAllAppointments();
    }

    // Fetch Appointment By ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Appointment>> getAppointmentById(@PathVariable Long id){

        return appointmentService.getAppointmentById(id);
    }

    // Fetch Appointment By DateTime
    @GetMapping("/date/{dateTime}")
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDate(@PathVariable LocalDateTime dateTime){

        return appointmentService.getAppointmentByDate(dateTime);
    }

    // Fetch Appointment By Doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(@PathVariable Long doctorId){

        return appointmentService.getAppointmentByDoctor(doctorId);
    }

    // Fetch Appointment By Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ResponseStructure<List<Appointment>>>
    getAppointmentByPatient(@PathVariable Long patientId){

        return appointmentService.getAppointmentByPatient(patientId);
    }

    // Fetch Appointment By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByStatus(@PathVariable Status status){

        return appointmentService.getAppointmentByStatus(status);
    }

    // Cancel Appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(@PathVariable Long id){

        return appointmentService.cancelAppointment(id);
    }

    // Update Appointment Status
    @PutMapping("/{id}/{status}")
    public ResponseEntity<ResponseStructure<Appointment>> updateAppointmentStatus(@RequestBody Appointment appointment, @PathVariable Long id){

        return appointmentService.updateAppointmentStatus(appointment, id);
    }
}

