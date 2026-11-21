package com.hospitalsystem.model;

/**
 * Member 2 - Base Person class.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Person {

    private final String id;
    private final String fullName;
    private final int ageInYears;

    public Person(String id, String fullName, int ageInYears) {
        this.id = id;
        this.fullName = fullName;
        this.ageInYears = ageInYears;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return fullName;
    }

    public int getAge() {
        return ageInYears;
    }

    public abstract String getRole();

    public String getDetails() {
        return "[" + getRole() + "] ID: " + id + " | Name: " + fullName + " | Age: " + ageInYears;
    }
}
