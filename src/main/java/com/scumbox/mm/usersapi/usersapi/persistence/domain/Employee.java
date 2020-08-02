package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
public class Employee {

    @Id
    private String id;

    @NotBlank
    @Size(max = 8)
    private Integer dni;

    private String tipoDni;

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

    private List<String> telefonos;

    private String cuil;

    private Date fechaNacimiento;

    private List<Integer> turnos;


    private String imageProfile;

}
