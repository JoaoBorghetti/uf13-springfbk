package it.afp.spring.purchase.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class UserDetails {
    @Id
    public String id;
    public String email;
    public String name;
    public String surname;
    public String password;
}
