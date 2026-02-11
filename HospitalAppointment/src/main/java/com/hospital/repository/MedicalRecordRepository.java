package com.hospital.repository;

import com.hospital.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByPatientPatientId(Long patientId);

    List<MedicalRecord> findByDoctorDoctorId(Long doctorId);

    List<MedicalRecord> findByVisitDate(LocalDate visitDate);
}

