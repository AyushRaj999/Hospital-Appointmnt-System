package com.hospital.service;

import com.hospital.dao.PatientDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientDAO patientDAO;

    // Register Patient
    public ResponseEntity<ResponseStructure<Patient>> registerPatient(Patient patient){
        ResponseStructure<Patient> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Patient Register Successfully");
        response.setData(patientDAO.addPatient(patient));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch All Patient
    public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatient(){
        ResponseStructure<List<Patient>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All Patient record get Successfully");
        response.setData(patientDAO.getAlPatient());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch patient by id
    public ResponseEntity<ResponseStructure<Patient>> getPatientById(Long id){
        ResponseStructure<Patient> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Patient Record get by id "+id+" Successfully");
        response.setData(patientDAO.getPatientById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Patient by Phone Number
    public ResponseEntity<ResponseStructure<Patient>> getPatientByPhoneNumber(String phoneNumber){
        ResponseStructure<Patient> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient Record get by Phone number "+phoneNumber+" Successfully");
        response.setData(patientDAO.getPatientByPhoneNumber(phoneNumber));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Patient Age Greater than {age}
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatientByAge(Integer age){
        ResponseStructure<List<Patient>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient Record get by age greater than "+age+" Successfully");
        response.setData(patientDAO.getPatientAgeGreaterThan(age));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch by Appointment
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatientByAppointmentDate(LocalDateTime appointmentDateAndTime){
        ResponseStructure<List<Patient>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient Record get by age Appointment Date "+appointmentDateAndTime+" Successfully");
        response.setData(patientDAO.getPatientByAppointmentDate(appointmentDateAndTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch by Medical Record
    public ResponseEntity<ResponseStructure<List<Patient>>> getPatientByMedicalRecord(Long recordId){
        ResponseStructure<List<Patient>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient Record get by Medical Record "+recordId+" Successfully");
        response.setData(patientDAO.getPatientByMedicalRecord(recordId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Patient
    public ResponseEntity<ResponseStructure<Patient>> updatePatient(Patient patient, Long id){
        ResponseStructure<Patient> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient record update successfully");
        response.setData(patientDAO.updatePatientInfo(patient, id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Patient
    public ResponseEntity<ResponseStructure<Patient>> deletePatient(Long id){
        ResponseStructure<Patient> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Patient record Delete successfully");
        patientDAO.deletePatient(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
