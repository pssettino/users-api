package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer documentNumber;

    private List<JustificationDetail> justificationDetail;

    public Justification(@NotBlank Integer documentNumber, List<JustificationDetail> justificationDetail) {
        this.documentNumber = documentNumber;
        this.justificationDetail = justificationDetail;
    }
}
