package com.hospital.repository;

import com.hospital.entity.Appointment;
import com.hospital.enu.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAppointmentDateTime(LocalDateTime dateTime);

    List<Appointment> findByDoctorDoctorId(Long doctorId);

    List<Appointment> findByPatientPatientId(Long patientId);

    List<Appointment> findByStatus(Status status);

    boolean existsByDoctorDoctorIdAndAppointmentDateTime(Long doctorId, LocalDateTime dateTime);

    boolean existsByPatientPatientIdAndAppointmentDateTime(Long patientId, LocalDateTime dateTime);

    @Query(value = """
        SELECT COUNT(*) > 0
        FROM appointments a
        WHERE a.patient_id = :patientId
        AND DATE(a.appointment_date_time) = :date
        """, nativeQuery = true)
    boolean existsPatientAppointmentOnDate(Long patientId, LocalDate date);
}

