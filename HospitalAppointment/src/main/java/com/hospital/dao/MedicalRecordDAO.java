package com.hospital.dao;

import com.hospital.entity.Appointment;
import com.hospital.entity.MedicalRecord;
import com.hospital.enu.Status;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.exception.SlotNotAvailableException;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MedicalRecordDAO {

    @Autowired
    private MedicalRecordRepository repository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    // Save Medical Record
    public MedicalRecord saveRecord(MedicalRecord record){
        Long patientId = record.getPatient().getPatientId();
        Long doctorId = record.getDoctor().getDoctorId();
        LocalDate visitDate = record.getVisitDate();

        List<Appointment> appointments = appointmentRepository.findByDoctorDoctorId(doctorId);

        boolean completed = appointments.stream()
                .anyMatch(a -> a.getPatient().getPatientId().equals(patientId)
                                && a.getAppointmentDateTime().toLocalDate().equals(visitDate)
                                && a.getStatus() == Status.COMPLETED
                );
        if(!completed){
            throw new SlotNotAvailableException(
                    "Medical record allowed only after completed appointment");
        }
        return repository.save(record);
    }

    // Fetch All Medical Record
    public List<MedicalRecord> getAllRecords(){
        List<MedicalRecord> medicalRecords = repository.findAll();
        if(medicalRecords.isEmpty()){
            throw new NoRecordAvailableException("No Record Available in Database");
        }
        return medicalRecords;
    }

    // Fetch Medical Record By ID
    public MedicalRecord getRecordById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Record not found"));
    }

    // Fetch Medical Record By Patient
    public List<MedicalRecord> getRecordByPatient(Long patientId){
        List<MedicalRecord> medicalRecords = repository.findByPatientPatientId(patientId);
        if (medicalRecords.isEmpty()){
            throw new NoRecordAvailableException("No Record Available in Database");
        }
        return medicalRecords;
    }

    // Fetch Medical Record By Doctor
    public List<MedicalRecord> getRecordByDoctor(Long doctorId){
        List<MedicalRecord> medicalRecords = repository.findByDoctorDoctorId(doctorId);
        if(medicalRecords.isEmpty()){
            throw new NoRecordAvailableException("No Record Available in Database");
        }
        return medicalRecords;
    }

    // Fetch Medical Record By Date
    public List<MedicalRecord> getRecordByVisitDate(LocalDate date){
        List<MedicalRecord> medicalRecords = repository.findByVisitDate(date);
        if (medicalRecords.isEmpty()){
            throw new NoRecordAvailableException("No Record Available in Database");
        }
        return medicalRecords;
    }
}

