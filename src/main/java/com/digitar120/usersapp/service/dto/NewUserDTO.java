package com.digitar120.usersapp.service.dto;

import lombok.*;

@Getter @Setter @ToString @RequiredArgsConstructor @AllArgsConstructor
public class NewUserDTO {
    private Integer id;
    private String name;
    private String lastName;
}
