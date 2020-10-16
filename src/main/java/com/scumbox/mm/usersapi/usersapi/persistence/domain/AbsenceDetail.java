package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "absences_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceDetail implements Serializable {
    @Id
    private String id;
    private String absenceId;
    private Date start;
    private Date end;
    private String type;
    private String description;
    @JsonIgnore
    private Boolean status;
}
