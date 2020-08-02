package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "justificaciones")
@Setter
@Getter
@AllArgsConstructor
public class Justificaciones {

    @Id
    private String id;

    @NotBlank
    private Integer dni;

    private String justificacion;
}
