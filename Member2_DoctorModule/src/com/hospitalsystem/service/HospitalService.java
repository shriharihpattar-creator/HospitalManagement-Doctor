package com.hospitalsystem.service;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.util.DoctorSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member 2 - Service Layer
 * Focus on Doctor Directory management, fee updates, and Selection Sort utility.
 */
public class HospitalService {

    private final Map<String, Doctor> doctorDirectory = new HashMap<>();
    private final Map<String, Patient> patientRegistry = new HashMap<>();
    private final List<Appointment> appointments = new ArrayList<>();

    private int tokenNumber = 101;

    public HospitalService() {
        // Initial doctor and patient data
        doctorDirectory.put("D1", new Doctor("D1", "Rajesh Sharma", 45, "Cardiologist", 800.0, "Mon, Wed, Fri"));
        doctorDirectory.put("D2", new Doctor("D2", "Priya Patel", 38, "General Physician", 400.0, "Tue, Thu, Sat"));
        doctorDirectory.put("D3", new Doctor("D3", "Amit Verma", 50, "Orthopedic", 600.0, "Mon, Tue, Thu"));

        patientRegistry.put("P1", new Patient("P1", "Rohan Gupta", 30, "Fever & Cough"));
        patientRegistry.put("P2", new Patient("P2", "Sneha Rao", 25, "Joint Pain"));
    }

    public void addDoctor(String id, String name, int age, String specialization, double fee, String availableDays) {
        if (doctorDirectory.containsKey(id)) {
            throw new IllegalArgumentException("Doctor ID " + id + " is already present in directory.");
        }
        if (fee < 0) {
            throw new IllegalArgumentException("Fee cannot be less than zero.");
        }
        Doctor doc = new Doctor(id, name, age, specialization, fee, availableDays);
        doctorDirectory.put(id, doc);
    }

    public void registerPatient(String id, String name, int age, String disease) {
        if (patientRegistry.containsKey(id)) {
            throw new IllegalArgumentException("Patient ID " + id + " is already registered.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age entered: " + age);
        }
        patientRegistry.put(id, new Patient(id, name, age, disease));
    }

    public Appointment bookAppointment(String patientId, String doctorId, String date, boolean isEmergency)
            throws PatientNotFoundException, DoctorNotFoundException {

        Patient patient = patientRegistry.get(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Could not find patient ID: " + patientId);
        }

        Doctor doctor = doctorDirectory.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Could not find doctor ID: " + doctorId);
        }

        String apptCode = "APT" + (tokenNumber++);
        Appointment appointment = new Appointment(apptCode, patient, doctor, date, isEmergency);

        if (isEmergency) {
            appointments.add(0, appointment); // Priority insertion
        } else {
            appointments.add(appointment);
        }

        return appointment;
    }

    public void payBill(String appointmentId) {
        for (Appointment a : appointments) {
            if (a.getId().equalsIgnoreCase(appointmentId)) {
                if (a.isPaid()) {
                    throw new IllegalArgumentException("Payment already made for appointment " + appointmentId);
                }
                a.markAsPaid();
                return;
            }
        }
        throw new IllegalArgumentException("No matching appointment found with ID: " + appointmentId);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctorDirectory.values());
    }

    // DSA Feature: Returns doctors sorted by consultation fee ascending
    public List<Doctor> getDoctorsSortedByFee() {
        List<Doctor> docList = new ArrayList<>(doctorDirectory.values());
        DoctorSorter.sortByFeeAscending(docList);
        return docList;
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patientRegistry.values());
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    public double getTotalRevenue() {
        double revenue = 0.0;
        for (Appointment a : appointments) {
            if (a.isPaid()) {
                revenue += a.getAmount();
            }
        }
        return revenue;
    }
}
