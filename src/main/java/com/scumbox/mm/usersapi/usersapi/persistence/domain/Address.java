package com.scumbox.mm.usersapi.usersapi.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String address;
    private String city;
    private String country;
    private String postalCode;
}
