package org.benjaminrperry.accountservice.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TokenDTO {
    private String token;
}
