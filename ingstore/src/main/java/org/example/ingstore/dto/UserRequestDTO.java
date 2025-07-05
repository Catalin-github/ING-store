package org.example.ingstore.dto;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private Set<String> roleNames;
}
