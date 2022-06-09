package com.hands.springdemo01.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormDto {
    private String name;
    private String email;
    private String password;
    private String address;
}
