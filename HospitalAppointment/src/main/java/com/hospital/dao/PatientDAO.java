package com.hospital.dao;

import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.exception.GreaterAgeException;
import com.hospital.exception.IdNotFoundException;
import com.hospital.exception.NoRecordAvailableException;
import com.hospital.exception.PhoneNumberNotExistsException;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientDAO {

    @Autowired
    private PatientRepository patientRepository;

    // Register Patient
    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    // Fetch All Patient
    public List<Patient> getAlPatient(){
        List<Patient> patients = patientRepository.findAll();
        if(!patients.isEmpty()){
            return patients;
        }
        else {
            throw new NoRecordAvailableException("Record Not available in Database");
        }
    }
    // Fetch Patient By id
    public Patient getPatientById(Long id){
        Optional<Patient> optional = patientRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            throw new IdNotFoundException("Id Not Present in Database");
        }
    }

    // Fetch Patient by phone number
    public Patient getPatientByPhoneNumber(String phoneNumber){
        Optional<Patient> optional = patientRepository.findByPhoneNumber(phoneNumber);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            throw new PhoneNumberNotExistsException("Given Phone Number is not exists in Database");
        }
    }

    // Fetch Patient where age greater than {age}
    public List<Patient> getPatientAgeGreaterThan(Integer age){
        List<Patient> patients = patientRepository.findByAgeGreaterThan(age);
        if (patients.isEmpty()){
            throw new GreaterAgeException("No any age greater then :"+age);
        }
        else {
            return patients;
        }
    }

    // Fetch Patient by Appointment date and time
    public List<Patient> getPatientByAppointmentDate(LocalDateTime appointmentDateTime){
        List<Patient> patients = patientRepository.findByAppointmentsAppointmentDateTime(appointmentDateTime);
         if (patients.isEmpty()){
             throw new NoRecordAvailableException("No Record Available for Given date");
         }
         else{
             return patients;
         }
    }

    // Fetch patient By medical record
    public List<Patient> getPatientByMedicalRecord(Long recordId){
        List<Patient> patients = patientRepository.findByMedicalRecordsRecordId(recordId);
        if (patients.isEmpty()){
            throw new NoRecordAvailableException("No record Available in Database");
        }
        return patients;
    }

    // Update Patient
    public Patient updatePatientInfo(Patient patient, Long id){
        Patient exist = patientRepository.findById(id)
                .orElseThrow(()->new IdNotFoundException("Id Not Found fr Update"));
        exist.setPatientName(patient.getPatientName());
        exist.setAge(patient.getAge());
        exist.setGender(patient.getGender());
        exist.setPhoneNumber(patient.getPhoneNumber());
        exist.setEmail(patient.getEmail());
        return patientRepository.save(exist);
    }

    // Delete Patient
    public void deletePatient(Long id){
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()){
            patientRepository.delete(optional.get());
        }
        else{
            throw new IdNotFoundException("Id not fond in Database");
        }
    }
}
