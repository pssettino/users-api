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

@Document(collection = "santions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sanction {
    @Id
    private String id;
    @NotBlank
    private Integer documentNumber;
    private List<SanctionDetail> sanctionDetail;

    public Sanction(@NotBlank Integer documentNumber, List<SanctionDetail> sanctionDetail) {
        this.documentNumber = documentNumber;
        this.sanctionDetail = sanctionDetail;
    }
}
