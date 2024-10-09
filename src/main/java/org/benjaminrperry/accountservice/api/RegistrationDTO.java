package org.benjaminrperry.accountservice.api;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
