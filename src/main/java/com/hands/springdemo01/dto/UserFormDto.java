package com.hands.springdemo01.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserFormDto {
    @NotBlank(message = "name must be included")
    private String name;

    @NotEmpty(message = "email must be included")
    @Email(message = "insert email type")
    private String email;

    @NotEmpty(message = "password must be included")
    @Length(min = 8, max = 16, message = "password must between 8 to 15 letters")
    private String password;

    @NotEmpty(message = "address must be included")
    private String address;
}
