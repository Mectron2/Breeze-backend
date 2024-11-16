package org.app.breeze.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class CurrentUserDTO {
    private UserDTO user;
    private Collection<GrantedAuthority> authorities;
}
