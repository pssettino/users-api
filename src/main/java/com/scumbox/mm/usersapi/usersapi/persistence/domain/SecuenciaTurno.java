package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "secuencia_turnos")
@Setter
@Getter
@AllArgsConstructor
public class SecuenciaTurno {

    @Id
    private String id;

    @NotBlank
    private Integer idTurno;

    @NotBlank
    private List<Integer> employees;

    @NotBlank
    private Integer orden;

    private String descripcion;
}