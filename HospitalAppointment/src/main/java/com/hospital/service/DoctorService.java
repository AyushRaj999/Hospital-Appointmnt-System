package com.hospital.service;

import com.hospital.dao.DoctorDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    // Add Doctor
    public ResponseEntity<ResponseStructure<Doctor>> addDoctor(Doctor doctor){
        ResponseStructure<Doctor> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Doctor Added Successfully");
        response.setData(doctorDAO.saveDoctor(doctor));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch All Doctor
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctor(){
        ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
        List<Doctor> doctors = doctorDAO.getAllDoctor();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Doctor Fetch Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctor by id
    public ResponseEntity<ResponseStructure<Doctor>> getDoctorById(Long id){
        ResponseStructure<Doctor> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Successfully fetch doctor by id");
        response.setData(doctorDAO.getDoctorById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctor By Specialization
    public ResponseEntity<ResponseStructure<Doctor>> getDoctorBySpecialization(String specialization){
        ResponseStructure<Doctor> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors By Specialization Successfully");
        response.setData(doctorDAO.getDoctorBySpecialization(specialization));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctors in a department
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctorByDepartment(String departmentName){
        ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
        List<Doctor> doctors = doctorDAO.getDoctorsByDepartment(departmentName);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors in department Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctors by Patients
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctorByPatient(String patientName){
        ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
        List<Doctor> doctors = doctorDAO.getDoctorsByPatient(patientName);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors by Patient Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctors By Appointment
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctorByAppointment(LocalDateTime availableDateAndTime){
        ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
        List<Doctor> doctors = doctorDAO.getDoctorByAppointment(availableDateAndTime);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors by available Date and Time Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Doctors by AvailableDay
    public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctorByAvailableDays(String availability){
        ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
        List<Doctor> doctors = doctorDAO.getDoctorsByAvailableDays(availability);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors with their availability Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Doctors
    public ResponseEntity<ResponseStructure<Doctor>> updateDoctors(Doctor doctor, Long id){
        ResponseStructure<Doctor> response = new ResponseStructure<>();
        Doctor doctors = doctorDAO.updateDoctors(doctor,id);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors in department Successfully");
        response.setData(doctors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Doctor
    public ResponseEntity<ResponseStructure<Doctor>> deleteDoctor(Long id){
        ResponseStructure<Doctor> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Fetch Doctors in department Successfully");
//        response.setData(doctorDAO.deleteDoctor(id));
        doctorDAO.deleteDoctor(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
