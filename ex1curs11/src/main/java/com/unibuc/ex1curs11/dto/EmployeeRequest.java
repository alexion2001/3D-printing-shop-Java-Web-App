package com.unibuc.ex1curs11.dto;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Employee request", description = "Required details needed to create a new Employee")
public class EmployeeRequest {

    @NotBlank
    @ApiModelProperty(value = "fullName", required = true, notes = "The name of the Employee", example = "John Doe", position = 1)
    private String fullName;
    @NotBlank
    @ApiModelProperty(value = "mobileNumber", required = true, notes = "The mobileNumber of the Employee", example = "0728282106", position = 2)
    private String mobileNumber;
    @NotNull
    @ApiModelProperty(value = "salary", required = true, notes = "The salary of the Employee", example = "2500", position = 3)
    private long  salary;
    @NotBlank
    @ApiModelProperty(value = "job", required = true, notes = "The job of the Employee", example = "Proiectant", position = 1)
    private String job;

    public EmployeeRequest() {

    }
    public EmployeeRequest(String fullName, String mobileNumber, long salary, String job) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.salary = salary;
        this.job = job;
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
}
