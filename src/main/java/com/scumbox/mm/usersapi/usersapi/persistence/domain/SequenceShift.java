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
import java.util.List;

@Document(collection = "sequence_shifts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SequenceShift {
    @Id
    private String id;

    @NotBlank
    private Integer shiftId;

    @NotBlank
    private List<Integer> employees;

    @NotBlank
    private Integer orden;

    private String description;
}
