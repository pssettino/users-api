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
}
