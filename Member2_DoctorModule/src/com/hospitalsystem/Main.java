package com.hospitalsystem;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.service.HospitalService;

import java.util.List;
import java.util.Scanner;

/**
 * Member 2 - Doctor Catalog & Scheduling System
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final HospitalService service = new HospitalService();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM - MEMBER 2 (DSA)    ");
        System.out.println("==================================================");

        boolean keepRunning = true;
        while (keepRunning) {
            showMenu();
            int choice = promptInt("Select an option (1-7): ");

            switch (choice) {
                case 1:
                    addDoctorRecord();
                    break;
                case 2:
                    viewDoctorsSorted();
                    break;
                case 3:
                    registerPatientRecord();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    viewScheduledAppointments();
                    break;
                case 6:
                    settleAppointmentBill();
                    break;
                case 7:
                    keepRunning = false;
                    System.out.println("Exiting Doctor Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please choose 1 to 7.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- DOCTOR & CLINIC DASHBOARD ---");
        System.out.println("1. Add New Doctor to Directory");
        System.out.println("2. View Doctors (Sorted by Fee - Selection Sort DSA)");
        System.out.println("3. Register New Patient");
        System.out.println("4. Book Patient Appointment");
        System.out.println("5. View All Appointments (Emergency first)");
        System.out.println("6. Settle Patient Bill");
        System.out.println("7. Exit System");
        System.out.println("----------------------------------");
    }

    private static void addDoctorRecord() {
        System.out.println("\n[Add Doctor]");
        String id = promptString("Doctor ID: ");
        String name = promptString("Doctor Name: ");
        int age = promptInt("Doctor Age: ");
        String spec = promptString("Specialization (e.g. Cardiologist): ");
        double fee = promptDouble("Consultation Fee (Rs): ");
        String days = promptString("Working Days (e.g. Mon, Wed, Fri): ");

        try {
            service.addDoctor(id, name, age, spec, fee, days);
            System.out.println("Doctor Dr. " + name + " added to the system.");
        } catch (IllegalArgumentException e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    private static void viewDoctorsSorted() {
        System.out.println("\n[Doctor Directory - Sorted by Fee Ascending]");
        List<Doctor> sortedDocs = service.getDoctorsSortedByFee();
        if (sortedDocs.isEmpty()) {
            System.out.println("No doctors in the directory.");
            return;
        }
        for (Doctor doc : sortedDocs) {
            System.out.println(doc.getDetails());
        }
    }

    private static void registerPatientRecord() {
        System.out.println("\n[Register Patient]");
        String id = promptString("Patient ID: ");
        String name = promptString("Patient Name: ");
        int age = promptInt("Patient Age: ");
        String symptoms = promptString("Symptoms / Disease: ");

        try {
            service.registerPatient(id, name, age, symptoms);
            System.out.println("Patient " + name + " successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
    }

    private static void scheduleAppointment() {
        System.out.println("\n[Schedule Appointment]");
        String pId = promptString("Patient ID: ");
        String dId = promptString("Doctor ID: ");
        String date = promptString("Date (YYYY-MM-DD): ");
        String emergency = promptString("Is Emergency? (yes/no): ");
        boolean isEmg = emergency.equalsIgnoreCase("yes") || emergency.equalsIgnoreCase("y");

        try {
            Appointment appt = service.bookAppointment(pId, dId, date, isEmg);
            System.out.println("Appointment booked successfully. Code: " + appt.getId());
        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            System.out.println("Booking error: " + e.getMessage());
        }
    }

    private static void viewScheduledAppointments() {
        System.out.println("\n[Scheduled Appointments]");
        List<Appointment> list = service.getAppointments();
        if (list.isEmpty()) {
            System.out.println("No appointments booked.");
            return;
        }
        for (Appointment a : list) {
            System.out.println(a.getDetails());
        }
    }

    private static void settleAppointmentBill() {
        System.out.println("\n[Billing]");
        viewScheduledAppointments();
        String id = promptString("Enter Appointment ID to settle: ");

        try {
            service.payBill(id);
            System.out.println("Payment processed successfully.");
            System.out.println("Total Hospital Earnings: Rs." + service.getTotalRevenue());
        } catch (IllegalArgumentException e) {
            System.out.println("Payment error: " + e.getMessage());
        }
    }

    private static String promptString(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static int promptInt(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid whole number.");
            }
        }
    }

    private static double promptDouble(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid decimal number.");
            }
        }
    }
}
