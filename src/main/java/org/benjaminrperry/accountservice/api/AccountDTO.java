package org.benjaminrperry.accountservice.api;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class AccountDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Instant joinDate;
}
