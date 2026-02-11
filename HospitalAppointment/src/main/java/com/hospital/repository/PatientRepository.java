package com.hospital.repository;

import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);
    List<Patient> findByAgeGreaterThan(Integer age);
    List<Patient> findByAppointmentsAppointmentDateTime(LocalDateTime appointmentDateTime);
    List<Patient> findByMedicalRecordsRecordId(Long recordId);

    // Fetch patient by medical record
}
