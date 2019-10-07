package com.aarush.gmain;

public class Registration {
    private String Name;
    private String Weight;
    private String Phone;
    private String Email;
    private String Goal;
    private String GymmingExperiance;
    private String Address;
    private String MedicalProblem;
    private String CurrentAddress;

    public Registration() {
    }

    public Registration(String name, String weight, String phone, String email, String goal, String gymingExperiance, String address, String medicalProblem, String currentAddress) {
        Name = name;
        Weight = weight;
        Phone = phone;
        Email = email;
        Goal = goal;
        GymmingExperiance = gymingExperiance;
        Address = address;
        MedicalProblem = medicalProblem;
        CurrentAddress = currentAddress;
    }

    public String getName() {
        return Name;
    }

    public String getWeight() {
        return Weight;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getGoal() {
        return Goal;
    }

    public String getGymmingExperiance() {
        return GymmingExperiance;
    }

    public String getAddress() {
        return Address;
    }

    public String getMedicalProblem() {
        return MedicalProblem;
    }

    public String getCurrentAddress() {
        return CurrentAddress;
    }
}
