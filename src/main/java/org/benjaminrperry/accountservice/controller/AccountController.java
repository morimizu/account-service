package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.AccountDTO;
import org.benjaminrperry.accountservice.configuration.EnableRestCallLogging;
import org.benjaminrperry.accountservice.converter.AccountConverter;
import org.benjaminrperry.accountservice.service.AccountService;
import org.benjaminrperry.accountservice.service.JWTGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.benjaminrperry.accountservice.converter.AccountConverter.convert;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final JWTGenerator jwtGenerator;

    @GetMapping("/accountInfo")
    @EnableRestCallLogging
    public AccountDTO getAccountInfo(WebRequest request) {
        var token = request.getHeader("Authorization");
        accountService.authorize(token, List.of("Get Account Info"));
        var claims = jwtGenerator.getClaims(token);
        return convert(accountService.getAccountByUsername(claims.getSubject()));
    }
}
