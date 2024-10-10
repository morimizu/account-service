package org.benjaminrperry.accountservice.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Instant joinDate;
}
