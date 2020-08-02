package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "turnos")
@Setter
@Getter
@AllArgsConstructor
public class Turno {

    @Id
    private String id;

    @NotBlank
    private Integer idTurno;

    @NotBlank
    @Size(max = 140)
    private String descripcion;

    @NotBlank
    private Date start;

    @NotBlank
    private Date end;
}
