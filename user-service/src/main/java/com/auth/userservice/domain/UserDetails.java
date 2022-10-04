package com.auth.userservice.domain;

import lombok.*;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class UserDetails {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String id;
    public String email;
    public String name;
    public String surname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;
}
