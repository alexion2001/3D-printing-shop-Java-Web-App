package com.unibuc.shop.model;



import javax.persistence.*;


@Table(name="employee")
@Entity
public class Employee {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String mobileNumber;
    private long  salary;
    private String job;

    public Employee() {
    }

    public Employee(Long employeeId, String fullName, String mobileNumber, long salary, String job) {
        this.id = employeeId;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.salary = salary;
        this.job = job;
    }

    public Employee(String fullName, String mobileNumber, long salary, String job) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.salary = salary;
        this.job = job;
    }

    public Long getEmployeeId() {
        return id;
    }

    public void setId(Long employeeId) {
        this.id = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}