package com.hospital.service;

import com.hospital.dao.MedicalRecordDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class MedicalRecordService {

    @Autowired
    private  MedicalRecordDAO dao;

    // Create Record
    public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(MedicalRecord record){

        ResponseStructure<MedicalRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Medical record created successfully");
        response.setData(dao.saveRecord(record));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch All Medical Record
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllRecords(){

        ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Medical records fetched successfully");
        response.setData(dao.getAllRecords());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Medical Record By ID
    public ResponseEntity<ResponseStructure<MedicalRecord>> getRecordById(Long id){
        ResponseStructure<MedicalRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Medical record fetched successfully");
        response.setData(dao.getRecordById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Medical Records By Patient
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByPatient(Long patientId){
        ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Medical records fetched by patient");
        response.setData(dao.getRecordByPatient(patientId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Medical Records By Doctor
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByDoctor(Long doctorId){

        ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Medical records fetched by doctor");
        response.setData(dao.getRecordByDoctor(doctorId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Medical Records By Visit Date
    public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByVisitDate(LocalDate date){

        ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Medical records fetched by visit date");
        response.setData(dao.getRecordByVisitDate(date));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

