package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SanctionDetail implements Serializable {
    private Date start;
    private Date end;
    private String description;
}
