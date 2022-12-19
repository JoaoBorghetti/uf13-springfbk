package it.afp.spring.userservice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class LoginDetails {
    public String email;
    public String password;
}
