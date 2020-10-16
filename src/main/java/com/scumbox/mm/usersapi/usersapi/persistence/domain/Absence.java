package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Document(collection = "absences")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Absence implements Serializable {
    @Id
    private String id;

    private String employeeId;

    private Integer documentNumber;

    public Absence(@NotBlank Integer documentNumber) {
        this.documentNumber = documentNumber;
    }
}
