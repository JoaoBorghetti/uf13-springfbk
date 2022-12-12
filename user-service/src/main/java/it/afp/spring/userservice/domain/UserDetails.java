package it.afp.spring.userservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

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
