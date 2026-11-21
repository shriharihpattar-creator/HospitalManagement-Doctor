package com.hospitalsystem.model;

/**
 * Member 2 - Patient Entity.
 */
public class Patient extends Person {

    private final String illnessSymptoms;

    public Patient(String patientId, String patientName, int patientAge, String illnessSymptoms) {
        super(patientId, patientName, patientAge);
        this.illnessSymptoms = illnessSymptoms;
    }

    public String getDisease() {
        return illnessSymptoms;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Symptoms: " + illnessSymptoms;
    }
}
