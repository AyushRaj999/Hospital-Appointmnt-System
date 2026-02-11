package com.hospital.dao;

import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Prescription;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PrescriptionDAO {

    @Autowired
    private  PrescriptionRepository repository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Save Prescription
    public Prescription savePrescription(Prescription prescription){

        Long recordId = prescription.getMedicalRecord().getRecordId();
        MedicalRecord record = medicalRecordRepository.findById(recordId)
                               .orElseThrow(() -> new IdNotFoundException("Medical record not found"));

        prescription.setMedicalRecord(record);
        return repository.save(prescription);
    }


    // Fetch All
    public List<Prescription> getAll(){
    List<Prescription> prescriptions = repository.findAll();
    if (prescriptions.isEmpty()){
        throw new NoRecordAvailableException("No Record Available In Database");
    }
    else {
        return prescriptions;
    }
    }

    // Fetch Prescription By ID
    public Prescription getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Prescription not found"));
    }

    // Fetch Prescription By Medical Record
    public Prescription getByRecord(Long recordId){
        Prescription p = repository.findByMedicalRecordRecordId(recordId);
        if(p == null){
            throw new IdNotFoundException("Prescription not found for record");
        }
        return p;
    }

    // Fetch Prescription By Patient
    public List<Prescription> getByPatient(Long patientId){
        return repository.findByMedicalRecordPatientPatientId(patientId);
    }
}

