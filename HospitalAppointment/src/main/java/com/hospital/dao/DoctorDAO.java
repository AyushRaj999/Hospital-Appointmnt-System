package com.hospital.dao;

import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.exception.SpecializationNotFoundException;
import com.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorDAO {

    @Autowired
    private DoctorRepository doctorRepository;

    // Add Doctor
    public Doctor saveDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    // Fetch All Doctor
    public List<Doctor> getAllDoctor(){
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()){
            throw new NoRecordAvailableException("No Doctor Record Available in Database");

        }
        else{
            return doctors;
        }
    }

    // Fetch By id
    public Doctor getDoctorById(Long id){
        if(id==null){
            throw new IdNotFoundException("Id can not be null, please enter valid id ");
        }
        Optional<Doctor> optional = doctorRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            throw new IdNotFoundException("Id : "+id+" not found in Database");
        }
    }
    // Fetch Doctor by specialization
    public Doctor getDoctorBySpecialization(String specialization){
        Optional<Doctor> optional = doctorRepository.findBySpecializationIgnoreCase(specialization);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new SpecializationNotFoundException("Given specialization is not found in Database");
        }
    }

    // Fetch Doctors By Department
    public List<Doctor> getDoctorsByDepartment(String departmentName){
        List<Doctor> doctors = doctorRepository.findByDepartmentDepartmentNameIgnoreCase(departmentName);

        if(doctors.isEmpty()){
            throw new NoRecordAvailableException("Record is not available in Database");
        }
        return doctors;
    }

    // Fetch Doctors by Patient
    public List<Doctor> getDoctorsByPatient(String patientName){
        List<Doctor> doctors = doctorRepository.findByAppointmentsPatientPatientNameIgnoreCase(patientName);
        if(doctors.isEmpty()){
            throw new NoRecordAvailableException("Record is not available for Database");
        }
        return doctors;
    }

    // Fetch Doctors by Appointment
    public List<Doctor> getDoctorByAppointment(LocalDateTime appointmentDateTime){
        List<Doctor> doctors = doctorRepository.findByAppointmentsAppointmentDateTime(appointmentDateTime);
        if (doctors.isEmpty()){
            throw new NoRecordAvailableException("Record is not available for Database");
        }
        return doctors;
    }

    // Fetch Doctors By Available Days
    public List<Doctor> getDoctorsByAvailableDays(String availability){
        List<Doctor> doctors = doctorRepository.findByAvailabilityContainingIgnoreCase(availability);
        if (doctors.isEmpty()){
            throw new NoRecordAvailableException("No Doctor Available on " + availability);
        }
        return doctors;
    }


    //Update Doctors
    public Doctor updateDoctors(Doctor doctor, Long id){
        Doctor exist = doctorRepository.findById(id)
                .orElseThrow(()->new IdNotFoundException("Given Id is not present for Update"));
        exist.setDoctorName(doctor.getDoctorName());
        exist.setSpecialization(doctor.getSpecialization());
        exist.setAvailability(doctor.getAvailability());
        return doctorRepository.save(exist);
    }

    // Delete Doctor
    public void deleteDoctor(Long id){
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()){
            doctorRepository.delete(optional.get());
        }
        else {
            throw new IdNotFoundException("Given Id is not found for delete");
        }
    }
}
