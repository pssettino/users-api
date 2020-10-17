package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String id;

    @NotBlank
    @Size(max = 8)
    private Integer documentNumber;

    private String documentType;

    @NotBlank
    @Size(max = 20)
    private String fullName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private Date startDate;

    private Date endDate;

    @NotBlank
    private Boolean status;

    @NotBlank
    private ERole role;

    private Address address;

    private String cellphone;

    private String telephone;

    private String cuil;

    private Date birthday;

    private String shiftId;

    private String absenceId;

    private Boolean extraHoursAvailable;

    public Employee wrappEmployee(Employee employee) {
        this.documentNumber = employee.getDocumentNumber();
        this.documentType = employee.getDocumentType();
        this.fullName = employee.getFullName();
        this.email = employee.getEmail();
        this.startDate = employee.getStartDate();
        this.endDate = employee.getEndDate();
        this.status = employee.getStatus();
        this.role = employee.getRole();
        this.address = employee.getAddress();
        this.cellphone = employee.getCellphone();
        this.telephone = employee.getTelephone();
        this.cuil = employee.getCuil();
        this.birthday = employee.getBirthday();
        this.shiftId = employee.getShiftId();
        this.absenceId = employee.absenceId;
        this.extraHoursAvailable = employee.getExtraHoursAvailable();

        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    public Boolean getExtraHoursAvailable() {
        return extraHoursAvailable;
    }

    public void setExtraHoursAvailable(Boolean extraHoursAvailable) {
        this.extraHoursAvailable = extraHoursAvailable;
    }
}
