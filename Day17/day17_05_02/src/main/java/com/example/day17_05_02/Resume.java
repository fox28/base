package com.example.day17_05_02;

/**
 * Created by apple on 2017/3/9.
 */

public class Resume {

    private String property;
    private String expireJob;
    private String address;
    private String salary;

    public Resume(String property, String expirJob, String address, String salary) {
        this.property = property;
        this.expireJob = expirJob;
        this.address = address;
        this.salary = salary;
    }

    public String getProperty() {

        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getExpireJob() {
        return expireJob;
    }

    public void setExpireJob(String expireJob) {
        this.expireJob = expireJob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "property='" + property + '\'' +
                ", expireJob='" + expireJob + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
