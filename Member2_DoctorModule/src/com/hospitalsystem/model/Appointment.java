package com.hospitalsystem.model;

/**
 * Member 2 - Appointment Record.
 */
public class Appointment {

    private final String appointmentCode;
    private final Patient patientRecord;
    private final Doctor doctorAssigned;
    private final String appointmentDate;
    private final boolean emergencyCase;
    private boolean isSettled;

    public Appointment(String appointmentCode, Patient patientRecord, Doctor doctorAssigned, String appointmentDate, boolean emergencyCase) {
        this.appointmentCode = appointmentCode;
        this.patientRecord = patientRecord;
        this.doctorAssigned = doctorAssigned;
        this.appointmentDate = appointmentDate;
        this.emergencyCase = emergencyCase;
        this.isSettled = false;
    }

    public String getId() {
        return appointmentCode;
    }

    public Patient getPatient() {
        return patientRecord;
    }

    public Doctor getDoctor() {
        return doctorAssigned;
    }

    public String getDate() {
        return appointmentDate;
    }

    public boolean isEmergency() {
        return emergencyCase;
    }

    public double getAmount() {
        return doctorAssigned.getFee();
    }

    public boolean isPaid() {
        return isSettled;
    }

    public void markAsPaid() {
        this.isSettled = true;
    }

    public String getDetails() {
        String priorityTag = emergencyCase ? "[EMERGENCY]" : "[REGULAR]";
        return "Appt: " + appointmentCode + " | " + priorityTag + " | Patient: " + patientRecord.getName()
                + " | Doctor: Dr. " + doctorAssigned.getName() + " (" + doctorAssigned.getSpecialization() + ")"
                + " | Date: " + appointmentDate + " | Fee: Rs." + getAmount() + " | Settled: " + (isSettled ? "YES" : "NO");
    }
}
