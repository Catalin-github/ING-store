package org.example.ingstore.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponseDTO {
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> roles;
}