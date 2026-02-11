package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findBySpecializationIgnoreCase(String specialization);
    List<Doctor> findByDepartmentDepartmentNameIgnoreCase(String departmentName);
    List<Doctor> findByAppointmentsPatientPatientNameIgnoreCase(String patientName);
    List<Doctor> findByAppointmentsAppointmentDateTime(LocalDateTime appointmentDateTime);
    List<Doctor> findByAvailabilityContainingIgnoreCase(String day);

}
