package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection = "shifts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shift {

    @Id
    @JsonIgnore
    private String id;

    @NotBlank
    private Integer shiftId;

    @NotBlank
    @Size(max = 140)
    private String description;

    private List<Integer> daysOfWeek;

    private Integer hour;

    private Integer minutes;

    private Boolean extraHoursAvailable;
}
