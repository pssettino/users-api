package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Document(collection = "justifications")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Justification {

    @Id
    private String id;

    @NotBlank
    private Integer dni;

    private List<JustificationDetail> justificationDetail;
}
