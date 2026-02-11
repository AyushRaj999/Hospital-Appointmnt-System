package com.hospital.service;

import com.hospital.dao.AppointmentDAO;
import com.hospital.dto.ResponseStructure;
import com.hospital.entity.Appointment;
import com.hospital.enu.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    // Book Appointment
    public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(Appointment appointment){

        ResponseStructure<Appointment> response = new ResponseStructure<>();

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Appointment Booked Successfully");
        response.setData(appointmentDAO.saveAppointment(appointment));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Fetch All Appointments
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments(){

        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();

        List<Appointment> appointments = appointmentDAO.getAll();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointments fetched successfully");
        response.setData(appointments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Appointment By ID
    public ResponseEntity<ResponseStructure<Appointment>> getAppointmentById(Long id){

        ResponseStructure<Appointment> response = new ResponseStructure<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointment fetched successfully by ID");
        response.setData(appointmentDAO.getById(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Appointment By Date
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDate(LocalDateTime dateTime){

        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();

        List<Appointment> appointments = appointmentDAO.getByDate(dateTime);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointments fetched by date successfully");
        response.setData(appointments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Appointment By Doctor
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(Long doctorId){

        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();

        List<Appointment> appointments = appointmentDAO.getByDoctor(doctorId);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointments fetched by doctor successfully");
        response.setData(appointments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Appointment By Patient
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByPatient(Long patientId){

        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();

        List<Appointment> appointments = appointmentDAO.getByPatient(patientId);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointments fetched by patient successfully");
        response.setData(appointments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fetch Appointment By Status
    public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByStatus(Status status){

        ResponseStructure<List<Appointment>> response = new ResponseStructure<>();

        List<Appointment> appointments = appointmentDAO.getByStatus(status);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointments fetched by status successfully");
        response.setData(appointments);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Appointment Status
    public ResponseEntity<ResponseStructure<Appointment>> updateAppointmentStatus(Appointment app, Long id){

        ResponseStructure<Appointment> response = new ResponseStructure<>();

        Appointment appointment = appointmentDAO.updateAppointment(app, id);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointment status updated successfully");
        response.setData(appointment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Cancel Appointment
    public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(Long id){

        ResponseStructure<Appointment> response = new ResponseStructure<>();

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointment cancelled successfully");
        appointmentDAO.cancelAppointment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

