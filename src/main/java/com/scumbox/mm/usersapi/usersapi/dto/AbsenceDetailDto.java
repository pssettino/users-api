package com.scumbox.mm.usersapi.usersapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AbsenceDetailDto implements Serializable {
    private Integer id;
    private String employeeId;
    private Date start;
    private Date end;
    private String type;
    private String description;
    private Boolean status;
}
