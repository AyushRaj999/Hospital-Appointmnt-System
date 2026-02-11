package com.hospital.dao;

import com.hospital.entity.Appointment;
import com.hospital.enu.Status;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.exception.SlotNotAvailableException;
import com.hospital.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public class AppointmentDAO {

    @Autowired
    private AppointmentRepository repository;

    // Save
    public Appointment saveAppointment(Appointment appointment){

        Long patientId = appointment.getPatient().getPatientId();
        LocalDate date = appointment.getAppointmentDateTime().toLocalDate();

        // Patient same day check
        if(repository.existsPatientAppointmentOnDate(patientId, date)){
            throw new SlotNotAvailableException(
                    "Patient already has appointment on this day");
        }

        return repository.save(appointment);
    }

    // Fetch all
    public List<Appointment> getAll(){
        List<Appointment> appointments = repository.findAll();
        if(appointments.isEmpty()){
            throw new NoRecordAvailableException("No record Available in database");
        }
        else {
            return appointments;
        }
    }

    // Fetch By ID
    public Appointment getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Appointment not found"));
    }

    // Fetch By Date
    public List<Appointment> getByDate(LocalDateTime dateTime){

       List<Appointment> appointments = repository.findByAppointmentDateTime(dateTime);
       if (appointments.isEmpty()){
           throw new NoRecordAvailableException("No record available in Database");
       }
       return appointments;
    }

    // Fetch By Doctor
    public List<Appointment> getByDoctor(Long doctorId){

        List<Appointment> appointments = repository.findByDoctorDoctorId(doctorId);
        if (appointments.isEmpty()){
            throw new NoRecordAvailableException("No record Available in Database");
        }
        return appointments;
    }

    // Fetch By Patient
    public List<Appointment> getByPatient(Long patientId){

        List<Appointment> appointments = repository.findByPatientPatientId(patientId);
        if (appointments.isEmpty()){
            throw new NoRecordAvailableException("No record available in Database");
        }
        return appointments;
    }

    // Fetch By Status
    public List<Appointment> getByStatus(Status status){

        List<Appointment> appointments = repository.findByStatus(status);
        if (appointments.isEmpty()){
            throw new NoRecordAvailableException("No Record Available in Database");
        }
        return appointments;
    }

    // Update
    public Appointment updateAppointment(Appointment appointment, Long id){
        Appointment exist = repository.findById(id)
                .orElseThrow(()->new IdNotFoundException("Id nt present in database"));
        exist.setAppointmentDateTime(appointment.getAppointmentDateTime());
        exist.setStatus(appointment.getStatus());
        return repository.save(exist);
    }

    // Cancel Appointment
    public void cancelAppointment(Long id){
        Optional<Appointment> optional = repository.findById(id);
        if(optional.isPresent()){
            repository.delete(optional.get());
        }
        else {
            throw new IdNotFoundException("Id not found in database");
        }
    }
}

