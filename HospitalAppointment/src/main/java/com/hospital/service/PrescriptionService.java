package com.hospital.service;

import com.hospital.dao.PrescriptionDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Prescription;
import com.hospital.exception.IdNotFoundException;
import com.hospital.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionDAO dao;
    private final MedicalRecordRepository
            medicalRecordRepository;

    // Create Prescription
    public ResponseEntity<ResponseStructure<Prescription>> createPrescription(Prescription prescription){
        ResponseStructure<Prescription> response = new ResponseStructure<>();

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Prescription created successfully");
        response.setData(dao.savePrescription(prescription));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch All
    public ResponseEntity<ResponseStructure<List<Prescription>>> getAll(){

        ResponseStructure<List<Prescription>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Prescriptions fetched successfully");
        response.setData(dao.getAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // By ID
    public ResponseEntity<ResponseStructure<Prescription>> getById(Long id){

        ResponseStructure<Prescription> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Prescription fetched successfully");
        response.setData(dao.getById(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // By Medical Record
    public ResponseEntity<ResponseStructure<Prescription>> getByRecord(Long recordId){

        ResponseStructure<Prescription> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Prescription fetched by record");
        response.setData(dao.getByRecord(recordId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // By Patient
    public ResponseEntity<ResponseStructure<List<Prescription>>> getByPatient(Long patientId){

        ResponseStructure<List<Prescription>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Prescriptions fetched by patient");
        response.setData(dao.getByPatient(patientId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

