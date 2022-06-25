package com.ttymonkey.webquizengine.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class User {
    @Email
    @Pattern(regexp=".+@.+\\..+")
    private final String email;

    @Size(min = 5)
    private final String password;
}
